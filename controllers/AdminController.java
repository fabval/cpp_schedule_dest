

package controllers;

import java.util.Scanner;
import java.util.InputMismatchException;

public class AdminController {
    private UserService userService;
    private int failedLoginAttempts;
    private int maxLoginAttempts;

    public AdminController(UserService userService, int maxLoginAttempts) {
        this.userService = userService;
        this.failedLoginAttempts = 0;
        this.maxLoginAttempts = maxLoginAttempts;
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                if (username.isEmpty()) {
                    System.out.println("Username cannot be empty!");
                    continue;
                }

                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                if (password.isEmpty()) {
                    System.out.println("Password cannot be empty!");
                    continue;
                }

                if (userService.compareCredentials(username, password)) {
                    System.out.println("Login successful!");
                    adminModules();
                    break;
                } else {
                    System.out.println("Invalid username or password!");
                    failedLoginAttempts++;
                    if (failedLoginAttempts < maxLoginAttempts) {
                        System.out.println("Remaining attempts: " + (maxLoginAttempts - failedLoginAttempts));
                    } else {
                        System.out.println("Maximum login attempts exceeded!");
                        System.exit(0);
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scanner.next(); // Clear invalid input
            }
        }
    }

    private void adminModules() {
        // TO DO: implement admin modules functionality
    }
}

class UserService {
    private String userId;
    private String password;

    public UserService(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public boolean compareCredentials(String username, String password) {
        return username.equals(userId) && password.equals(this.password);
    }
}