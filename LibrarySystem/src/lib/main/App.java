package lib.main;

import java.util.Scanner;
import lib.book.BookManager;
import lib.checkout.CheckoutManager;
import lib.member.MemberManager;
import lib.report.ReportGenerator;

public class App {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		// uncomment the two methods below to test connection and create tables
		// getConnection();
		// createTable();

		// Main menu option menu
		int intUserInput = 0; // main loop control variable
		String strUserInput = null;

		do {
			System.out.println("Select an option: ");
			System.out.println("1. Access Catalog");
			System.out.println("2. Member Accounts");
			System.out.println("3. Reports");
			System.out.println("4. Exit");
			strUserInput = scanner.nextLine();
			intUserInput = Integer.parseInt(strUserInput);

			switch (intUserInput) {
			case 1: // Access Catalog

				catalogSubMenu();

				break;
			case 2: // Member Accounts

				memberSubMenu();

				break;
			case 3: // Reports

				reportsSubMenu();

				break;
			case 4: // Exit
				// display a closing message and end the application when option 4 is selected
				System.out.println("Now exiting the library catalog.");
				// scanner.close();
				break;
			default:
				System.out.println("Invalid menu option entered. Try again.");
			}
		} while (intUserInput != 4);
	}

	// Access Catalog submenu
	public static void catalogSubMenu() {

		int intUserInput = 0; // main loop control variable
		String strUserInput = null;

		do {
			System.out.println("Select an option: ");
			System.out.println("1. Check-out a book");
			System.out.println("2. Check-in a book");
			System.out.println("3. Add a book");
			System.out.println("4. Update a book");
			System.out.println("5. Delete a book");
			System.out.println("6. Exit");
			strUserInput = scanner.nextLine();
			intUserInput = Integer.parseInt(strUserInput);

			switch (intUserInput) {
			case 1: // Check-out a book

				System.out.print("Enter the BookID of the book to check out: ");
				int bookIdToCheckOut = Integer.parseInt(scanner.nextLine());

				System.out.print("Enter the MemberID of the member checking out the book: ");
				int memberIdCheckingOut = Integer.parseInt(scanner.nextLine());

				CheckoutManager.checkOutBook(bookIdToCheckOut, memberIdCheckingOut);

				break;
			case 2: // Check-in a book

				System.out.print("Enter the BookID of the book to check in: ");
				int bookIdToCheckIn = Integer.parseInt(scanner.nextLine());

				System.out.print("Enter the MemberID of the member checking in the book: ");
				int memberIdCheckingIn = Integer.parseInt(scanner.nextLine());

				CheckoutManager.checkInBook(bookIdToCheckIn, memberIdCheckingIn);

				break;
			case 3: // Add a book

				System.out.print("Enter the book title: ");
				String bookTitle = scanner.nextLine();
				System.out.print("Enter the author's name: ");
				String author = scanner.nextLine();
				System.out.print("Enter the book category: ");
				String category = scanner.nextLine();

				BookManager.insertBook(bookTitle, author, category);

				break;
			case 4: // Update a book

				System.out.print("Enter the BookID of the book to update: ");
				int bookIdToUpdate = Integer.parseInt(scanner.nextLine());

				System.out.print("Enter the new title: ");
				String newTitle = scanner.nextLine();
				System.out.print("Enter the new author: ");
				String newAuthor = scanner.nextLine();
				System.out.print("Enter the new category: ");
				String newCategory = scanner.nextLine();

				BookManager.updateBook(bookIdToUpdate, newTitle, newAuthor, newCategory);

				break;
			case 5: // Delete a book

				System.out.print("Enter the BookID of the book to delete: ");
				int bookIdToDelete = Integer.parseInt(scanner.nextLine());

				BookManager.deleteBook(bookIdToDelete);

				break;
			case 6: // Exit
				// display a closing message and end the application when option 6 is selected
				System.out.println("Now exiting the catalog.");
				// scanner.close();
				break;
			default:
				System.out.println("Invalid menu option entered. Try again.");
			}
		} while (intUserInput != 6);
	}

	// Member Accounts submenu
	public static void memberSubMenu() {

		int intUserInput = 0; // main loop control variable
		String strUserInput = null;

		do {
			System.out.println("Select an option: ");
			System.out.println("1. Update a member account");
			System.out.println("2. Add a member account");
			System.out.println("3. Delete a member account");
			System.out.println("4. Exit");
			strUserInput = scanner.nextLine();
			intUserInput = Integer.parseInt(strUserInput);

			switch (intUserInput) {
			case 1: // Update a member account

				System.out.print("Enter the MemberID of the member to update: ");
				int memberId = Integer.parseInt(scanner.nextLine());

				System.out.print("Enter the updated first name: ");
				String newFirstName = scanner.nextLine();
				System.out.print("Enter the updated last name: ");
				String newLastName = scanner.nextLine();
				System.out.print("Enter the updated email: ");
				String newEmail = scanner.nextLine();

				MemberManager.updateMember(memberId, newFirstName, newLastName, newEmail);

				break;
			case 2: // Add a member account

				System.out.print("Enter the new member's first name: ");
				String firstName = scanner.nextLine();
				System.out.print("Enter the new member's last name: ");
				String lastName = scanner.nextLine();
				System.out.print("Enter the new member's email: ");
				String email = scanner.nextLine();

				MemberManager.insertMember(firstName, lastName, email);

				break;
			case 3: // Delete a member account

				System.out.print("Enter the MemberID of the member to delete: ");
				int memberIdToDelete = Integer.parseInt(scanner.nextLine());

				MemberManager.deleteMember(memberIdToDelete);

				break;
			case 4: // Exit
				// display a closing message and end the application when option 4 is selected
				System.out.println("Now exiting the catalog.");
				// scanner.close();
				break;
			default:
				System.out.println("Invalid menu option entered. Try again.");
			}
		} while (intUserInput != 4);
	}

	// Reports submenu
	public static void reportsSubMenu() {

		int intUserInput = 0; // main loop control variable
		String strUserInput = null;

		do {
			System.out.println("Select an option: ");
			System.out.println("1. View entire catalog");
			System.out.println("2. Show all checked-out books");
			System.out.println("3. Show all available books");
			System.out.println("4. Show checked-out books by each member");
			System.out.println("5. Show active member accounts");
			System.out.println("6. Show inactive member accounts");
			System.out.println("7. Exit");
			strUserInput = scanner.nextLine();
			intUserInput = Integer.parseInt(strUserInput);

			switch (intUserInput) {
			case 1: // View entire catalog

				ReportGenerator.viewEntireCatalog();

				break;
			case 2: // Show all checked-out books

				ReportGenerator.showCheckedOutBooks();

				break;
			case 3: // Show all available books

				ReportGenerator.showAvailableBooks();

				break;
			case 4: // Show checked-out books by each member

				ReportGenerator.showCheckedOutBooksByMember();

				break;
			case 5: // Show active member accounts

				ReportGenerator.showActiveMemberAccounts();

				break;
			case 6: // Show inactive member accounts

				ReportGenerator.showInactiveMemberAccounts();

				break;
			case 7: // Exit
				// display a closing message and end the application when option 6 is selected
				System.out.println("Now exiting the catalog.");
				// scanner.close();
				break;
			default:
				System.out.println("Invalid menu option entered. Try again.");
			}
		} while (intUserInput != 7);
	}
}
