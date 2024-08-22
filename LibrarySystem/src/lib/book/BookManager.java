package lib.book;

import java.sql.Connection;
import java.sql.PreparedStatement;

import lib.database.DatabaseConnection;

public class BookManager {

	public static void insertBook(String title, String author, String category) {
		try {
			Connection conn = DatabaseConnection.getConnection();

			// Insert a book into catalog
			String insertBookSQL = "INSERT INTO books (BookTitle, Author, Category) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(insertBookSQL);

			// Set values
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, author);
			preparedStatement.setString(3, category);

			// Execute
			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Book added successfully.");
			} else {
				System.out.println("Failed to add the book.");
			}

			preparedStatement.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteBook(int bookId) {
		try {
			Connection conn = DatabaseConnection.getConnection();

			// delete a book
			String deleteBookSQL = "DELETE FROM books WHERE BookID = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(deleteBookSQL);

			// Set value
			preparedStatement.setInt(1, bookId);

			// Execute
			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Book deleted successfully.");
			} else {
				System.out.println("Failed to delete the book. Please check the BookID.");
			}

			preparedStatement.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateBook(int bookId, String newTitle, String newAuthor, String newCategory) {
		try {
			Connection conn = DatabaseConnection.getConnection();

			// update a book
			String updateBookSQL = "UPDATE books SET BookTitle=?, Author=?, Category=? WHERE BookID=?";
			PreparedStatement preparedStatement = conn.prepareStatement(updateBookSQL);

			// Set value
			preparedStatement.setString(1, newTitle);
			preparedStatement.setString(2, newAuthor);
			preparedStatement.setString(3, newCategory);
			preparedStatement.setInt(4, bookId);

			// Execute
			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Book updated successfully.");
			} else {
				System.out.println("Failed to update the book. Please check the BookID.");
			}

			preparedStatement.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
