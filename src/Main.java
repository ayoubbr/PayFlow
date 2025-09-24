import controller.AuthController;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        System.out.println("Starting PayFlow Management System...");

        AuthController authController = new AuthController();

        authController.startLogin();

        System.out.println("Application has terminated.");
    }
}