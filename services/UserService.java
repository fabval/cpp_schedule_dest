

package services;

import java.util.InputMismatchException;

public class UserService {

    private static final String VALID_USER_ID = "12345";
    private static final String VALID_PASSWORD = "password";

    public boolean validateInput(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean authenticate(String userId, String password) {
        return userId.equals(VALID_USER_ID) && password.equals(VALID_PASSWORD);
    }
}