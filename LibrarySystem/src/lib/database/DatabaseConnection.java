package lib.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseConnection {

	// main connection to the database
	public static Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/cen4333libdb";
			String username = "LibUser";
			String password = "LibAcct";
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
			return conn;
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

	// used to create the three tables in the database
	public static void createTable() throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement createTable1 = con.prepareStatement(
					"CREATE TABLE IF NOT EXISTS books(BookID int NOT NULL AUTO_INCREMENT, BookTitle varchar(255), Author varchar(255), Category varchar(255), PRIMARY KEY(BookID))");
			createTable1.executeUpdate();

			PreparedStatement createTable2 = con.prepareStatement(
					"CREATE TABLE IF NOT EXISTS members(MemberID int NOT NULL AUTO_INCREMENT, MemFirstName varchar(255), MemLastName varchar(255), MemEmail varchar(255), PRIMARY KEY(MemberID))");
			createTable2.executeUpdate();

			PreparedStatement createTable3 = con.prepareStatement(
					"CREATE TABLE IF NOT EXISTS checkouts(CheckID int NOT NULL AUTO_INCREMENT, StartDate date, EndDate date, BookID int, MemberID int, CheckedOut boolean, PRIMARY KEY(CheckID), FOREIGN KEY(BookID) REFERENCES books(BookID), FOREIGN KEY(MemberID) REFERENCES members(MemberID))");
			createTable3.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Funtion Complete");
		}
		;
	}

}
