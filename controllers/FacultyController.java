

package controllers;

import java.util.Scanner;
import services.FacultyService;
import models.Faculty;

public class FacultyController {

    FacultyService facultyService;
    Faculty fac;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
        this.fac = new Faculty();
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        int failedAttempts = 0;
        while (true) {
            try {
                System.out.print("Enter your username: ");
                String username = scanner.nextLine();
                System.out.print("Enter your password: ");
                String password = scanner.nextLine();
                System.out.print("Enter the technology name: ");
                String technologyName = scanner.nextLine();
                String userId = facultyService.checkCredentials(username, password, technologyName);
                if (userId != null) {
                    System.out.println("Login successful!");
                    fac.setLoginId(userId);
                    System.out.println("Login successful!");
                    break;
                } else {
                    failedAttempts++;
                    System.out.println("Invalid credentials. Try again.");
                    if (failedAttempts >= 3) {
                        System.out.println("Maximum attempts reached. Exiting...");
                        System.exit(1);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error reading input. Please try again.");
            }
        }
    }
}