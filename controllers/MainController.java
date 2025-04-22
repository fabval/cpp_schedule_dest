

package controllers;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainController {

    private AdminController adminController;
    private FacultyController facultyController;
    private UserService userService;
    private Scanner scanner;

    public MainController() {
        this.adminController = new AdminController();
        this.facultyController = new FacultyController();
        this.userService = new UserService();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println(getMenuOptions());
            handleUserInput();
        }
    }

    private String getMenuOptions() {
        return "1. Admin Login\n2. Faculty Login\n3. Exit";
    }

    private void handleUserInput() {
        try {
            int choice = scanner.nextInt();
            if (!userService.isValidInput(choice)) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                scanner.next(); // Clear invalid input
                scanner.nextLine(); // Ignore any remaining input
                handleUserInput();
            } else {
                switch (choice) {
                    case 1:
                        adminController.handleAdminLogin();
                        break;
                    case 2:
                        facultyController.handleFacultyLogin();
                        break;
                    case 3:
                        System.out.println("Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        handleUserInput();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Clear invalid input
            scanner.nextLine(); // Ignore any remaining input
            handleUserInput();
        }
    }
}