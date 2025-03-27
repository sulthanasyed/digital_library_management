package com.example.dlbms;

import java.util.Arrays;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.dlbms.commands.CommandInvoker;

@SpringBootApplication
public class DlbmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DlbmsApplication.class, args);
		ApplicationConfig applicationConfig = new ApplicationConfig();
		CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();
		Scanner scanner = new Scanner(System.in);
		try {

			while (true) {
				System.out.println("\nLibrary System Menu:");
				System.out.println("1. Add Book");
				System.out.println("2. View All Books");
				System.out.println("3. Search Book By ID");
				System.out.println("4. Search Book By Title");
				System.out.println("5. Update Book Details");
				System.out.println("6. Update Book Availability");
				System.out.println("7. Delete Book");
				System.out.println("8. Exit");
				System.out.print("Choose an option: ");

				int choice = scanner.nextInt();
				scanner.nextLine();
				String id, title, author, genre, availability;

				switch (choice) {
				case 1:
					System.out.print("Enter Title: ");
					title = scanner.nextLine();
					System.out.print("Enter Author: ");
					author = scanner.nextLine();
					System.out.print("Enter Genre: ");
					genre = scanner.nextLine();
					System.out.print("Enter Availability (AVAILABLE/CHECKED_OUT): ");
					availability = scanner.nextLine();
					commandInvoker.executeCommand("ADD_BOOK", Arrays.asList(title, author, genre, availability));
					break;
				case 2:
					commandInvoker.executeCommand("VIEW_ALL", Arrays.asList());
					break;
				case 3:
					System.out.print("Enter Book ID : ");
					id = scanner.nextLine();
					commandInvoker.executeCommand("SEARCH_BOOK_ID", Arrays.asList(id));
					break;
				case 4:
					System.out.print("Enter Book Title : ");
					title = scanner.nextLine();
					commandInvoker.executeCommand("SEARCH_BOOK_TITLE", Arrays.asList(title));
					break;
				case 5:
					System.out.print("Enter Book ID to update: ");
					id = scanner.nextLine();
					System.out.print("Enter new Title: ");
					title = scanner.nextLine();
					System.out.print("Enter new Author: ");
					author = scanner.nextLine();
					System.out.print("Enter new genre: ");
					genre = scanner.nextLine();
					commandInvoker.executeCommand("UPDATE_BOOK_DETAILS", Arrays.asList(id, title, author, genre));
					break;
				case 6:
					System.out.print("Enter Book ID to update: ");
					id = scanner.nextLine();
					System.out.print("Enter new Availability (AVAILABLE/CHECKED_OUT): ");
					availability = scanner.nextLine();
					commandInvoker.executeCommand("UPDATE_BOOK_AVAILABILITY", Arrays.asList(id, availability));
					break;
				case 7:
					System.out.print("Enter Book ID to delete: ");
					id = scanner.nextLine();
					commandInvoker.executeCommand("DELETE_BOOK", Arrays.asList(id));
					break;
				case 8:
					System.out.println("Exiting system...");
					return;
				default:
					System.out.println("Invalid choice! Please try again.");
				}

			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}

}
