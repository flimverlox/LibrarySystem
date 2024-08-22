package lib.checkout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lib.database.DatabaseConnection;

public class CheckoutManager {

	public static void checkOutBook(int bookId, int memberId) {
		try {
			Connection conn = DatabaseConnection.getConnection();

			// want to have the system check if a book is already checked out, but is
			// getting complicated, so not implemented here yet
			// Get book information from the 'books' table
			String getBookInfo = "SELECT BookTitle, Author, Category FROM books WHERE BookID = ?";
			try (PreparedStatement getBookInfoPS = conn.prepareStatement(getBookInfo)) {
				getBookInfoPS.setInt(1, bookId);

				try (ResultSet bookInfoResultSet = getBookInfoPS.executeQuery()) {
					if (bookInfoResultSet.next()) {
						// Retrieve book information
						String bookTitle = bookInfoResultSet.getString("BookTitle");

						// Proceed with the check-out
						String checkOutBook = "INSERT INTO checkouts (StartDate, EndDate, BookID, MemberID, CheckedOut) VALUES (CURRENT_TIMESTAMP, NULL, ?, ?, true)";
						try (PreparedStatement checkOutBookPS = conn.prepareStatement(checkOutBook)) {
							checkOutBookPS.setInt(1, bookId);
							checkOutBookPS.setInt(2, memberId);

							int rowsAffected = checkOutBookPS.executeUpdate();

							if (rowsAffected > 0) {
								System.out.println(bookTitle + " checked out successfully by Member #" + memberId);
							} else {
								System.out
										.println("Failed to check out the book. Please check the BookID and MemberID.");
							}
						}
					} else {
						System.out.println("Book information not found in the 'books' table. Please check the BookID.");
					}
				}
			}

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void checkInBook(int bookId, int memberId) {
		try {
			Connection conn = DatabaseConnection.getConnection();

			// Proceed with check-in
			String checkInBook = "UPDATE checkouts SET EndDate = CURRENT_TIMESTAMP, CheckedOut = false WHERE BookID = ? AND MemberID = ?";
			try (PreparedStatement checkInBookPS = conn.prepareStatement(checkInBook)) {
				checkInBookPS.setInt(1, bookId);
				checkInBookPS.setInt(2, memberId);

				int rowsAffected = checkInBookPS.executeUpdate();

				if (rowsAffected > 0) {
					// to do: get the bookTitle to print...
					System.out.println("Book with ID " + bookId + " was returned by Member #" + memberId);
				} else {
					System.out.println("Failed to check in the book. Please check the BookID and MemberID.");
				}
			}

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
