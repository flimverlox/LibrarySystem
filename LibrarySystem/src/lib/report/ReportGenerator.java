package lib.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lib.database.DatabaseConnection;

public class ReportGenerator {

	public static void viewEntireCatalog() {
		try {
			Connection conn = DatabaseConnection.getConnection();
			String viewCatalogQuery = "SELECT * FROM books";
			try (PreparedStatement viewCatalogPS = conn.prepareStatement(viewCatalogQuery);
					ResultSet resultSet = viewCatalogPS.executeQuery()) {

				while (resultSet.next()) {
					int bookId = resultSet.getInt("BookID");
					String bookTitle = resultSet.getString("BookTitle");
					String author = resultSet.getString("Author");
					String category = resultSet.getString("Category");
					System.out.println("BookID: " + bookId + ", Title: " + bookTitle + ", Author: " + author
							+ ", Category: " + category);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showCheckedOutBooks() {
		try {
			Connection conn = DatabaseConnection.getConnection();
			String checkedOutBooksQuery = "SELECT b.* FROM books b JOIN checkouts c ON b.BookID = c.BookID WHERE c.CheckedOut = true";
			try (PreparedStatement checkedOutBooksPS = conn.prepareStatement(checkedOutBooksQuery);
					ResultSet resultSet = checkedOutBooksPS.executeQuery()) {

				while (resultSet.next()) {
					int bookId = resultSet.getInt("BookID");
					String bookTitle = resultSet.getString("BookTitle");
					String author = resultSet.getString("Author");
					String category = resultSet.getString("Category");
					System.out.println("BookID: " + bookId + ", Title: " + bookTitle + ", Author: " + author
							+ ", Category: " + category);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showAvailableBooks() {
		try {
			Connection conn = DatabaseConnection.getConnection();
			String availableBooksQuery = "SELECT * FROM books WHERE BookID NOT IN (SELECT BookID FROM checkouts WHERE CheckedOut = true)";
			try (PreparedStatement availableBooksPS = conn.prepareStatement(availableBooksQuery);
					ResultSet resultSet = availableBooksPS.executeQuery()) {

				while (resultSet.next()) {
					int bookId = resultSet.getInt("BookID");
					String bookTitle = resultSet.getString("BookTitle");
					String author = resultSet.getString("Author");
					String category = resultSet.getString("Category");
					System.out.println("BookID: " + bookId + ", Title: " + bookTitle + ", Author: " + author
							+ ", Category: " + category);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showCheckedOutBooksByMember() {
		try {
			Connection conn = DatabaseConnection.getConnection();
			String checkedOutBooksQuery = "SELECT c.MemberID, m.MemFirstName, m.MemLastName, b.BookID, b.BookTitle, b.Author, b.Category "
					+ "FROM checkouts c " + "JOIN members m ON c.MemberID = m.MemberID "
					+ "JOIN books b ON c.BookID = b.BookID " + "WHERE c.CheckedOut = true";
			try (PreparedStatement checkedOutBooksPS = conn.prepareStatement(checkedOutBooksQuery);
					ResultSet resultSet = checkedOutBooksPS.executeQuery()) {

				while (resultSet.next()) {
					int memberId = resultSet.getInt("MemberID");
					String memberFirstName = resultSet.getString("MemFirstName");
					String memberLastName = resultSet.getString("MemLastName");
					int bookId = resultSet.getInt("BookID");
					String bookTitle = resultSet.getString("BookTitle");
					String author = resultSet.getString("Author");
					String category = resultSet.getString("Category");

					System.out.println("MemberID: " + memberId + ", Member Name: " + memberFirstName + " "
							+ memberLastName + ", BookID: " + bookId + ", Title: " + bookTitle + ", Author: " + author
							+ ", Category: " + category);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showActiveMemberAccounts() {
		try {
			Connection conn = DatabaseConnection.getConnection();
			String activeMembersQuery = "SELECT DISTINCT m.MemberID, m.MemFirstName, m.MemLastName " + "FROM members m "
					+ "JOIN checkouts c ON m.MemberID = c.MemberID " + "WHERE c.CheckedOut = true";
			try (PreparedStatement activeMembersPS = conn.prepareStatement(activeMembersQuery);
					ResultSet resultSet = activeMembersPS.executeQuery()) {

				System.out.println("The following members currently have books checked out:");

				while (resultSet.next()) {
					int memberId = resultSet.getInt("MemberID");
					String memberFirstName = resultSet.getString("MemFirstName");
					String memberLastName = resultSet.getString("MemLastName");

					System.out.println(
							"MemberID: " + memberId + ", Member Name: " + memberFirstName + " " + memberLastName);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showInactiveMemberAccounts() {
		try {
			Connection conn = DatabaseConnection.getConnection();
			String inactiveMembersQuery = "SELECT MemberID, MemFirstName, MemLastName " + "FROM members "
					+ "WHERE MemberID NOT IN (SELECT MemberID FROM checkouts WHERE CheckedOut = true)";
			try (PreparedStatement inactiveMembersPS = conn.prepareStatement(inactiveMembersQuery);
					ResultSet resultSet = inactiveMembersPS.executeQuery()) {

				System.out.println("The following members currently have no books checked out:");

				while (resultSet.next()) {
					int memberId = resultSet.getInt("MemberID");
					String memberFirstName = resultSet.getString("MemFirstName");
					String memberLastName = resultSet.getString("MemLastName");

					System.out.println(
							"MemberID: " + memberId + ", Member Name: " + memberFirstName + " " + memberLastName);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
