

package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FacultyService {

    private Map<String, String> facultyLoginMap;

    public FacultyService() {
        facultyLoginMap = new HashMap<>();
        loadFacultyLoginData();
    }

    private void loadFacultyLoginData() {
        try (BufferedReader br = new BufferedReader(new FileReader("facultyLogin.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 2) {
                    facultyLoginMap.put(credentials[0], credentials[1]);
                } else {
                    throw new IOException("Invalid CSV file format");
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading faculty login data: " + e.getMessage());
        }
    }

    public boolean authenticate(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }
        if (facultyLoginMap == null) {
            return false;
        }
        return facultyLoginMap.containsKey(username) && facultyLoginMap.get(username).equals(password);
    }
}