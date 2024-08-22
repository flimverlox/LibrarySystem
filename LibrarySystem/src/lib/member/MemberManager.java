package lib.member;

import java.sql.Connection;
import java.sql.PreparedStatement;

import lib.database.DatabaseConnection;

public class MemberManager {

	public static void insertMember(String firstName, String lastName, String email) {
		try {
			Connection conn = DatabaseConnection.getConnection();

			// SQL statement to insert a new member into the members table
			String insertMemberSQL = "INSERT INTO members (MemFirstName, MemLastName, MemEmail) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(insertMemberSQL);

			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, email);

			// execute
			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Member added successfully.");
			} else {
				System.out.println("Failed to add the member.");
			}

			preparedStatement.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateMember(int memberId, String newFirstName, String newLastName, String newEmail) {
		try {
			Connection conn = DatabaseConnection.getConnection();

			// update member record
			String updateMemberSQL = "UPDATE members SET MemFirstName=?, MemLastName=?, MemEmail=? WHERE MemberID=?";
			PreparedStatement preparedStatement = conn.prepareStatement(updateMemberSQL);

			// Set update values
			preparedStatement.setString(1, newFirstName);
			preparedStatement.setString(2, newLastName);
			preparedStatement.setString(3, newEmail);
			preparedStatement.setInt(4, memberId);

			// Execute
			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Member updated successfully.");
			} else {
				System.out.println("Failed to update the member. Please check the MemberID.");
			}

			preparedStatement.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteMember(int memberId) {
		try {
			Connection conn = DatabaseConnection.getConnection();

			// delete a record
			String deleteMemberSQL = "DELETE FROM members WHERE MemberID = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(deleteMemberSQL);

			// Set value
			preparedStatement.setInt(1, memberId);

			// Execute
			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Member deleted successfully.");
			} else {
				System.out.println("Failed to delete the member. Please check the MemberID.");
			}

			preparedStatement.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
