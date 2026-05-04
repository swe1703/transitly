package com.swetha.transitly.features.authentication.adminsignin;

import com.swetha.transitly.features.admin.AdminDashboard;
import com.swetha.transitly.util.ConsoleInput;

import java.util.Scanner;

public class AdminSignInView {
    private AdminSignInModel adminSignInModel;
    private final Scanner scanner;

    public AdminSignInView() {
        this.adminSignInModel = new AdminSignInModel(this);
        this.scanner = ConsoleInput.getScanner();
    }

    public void init() {
        showAdminLoginMenu();
    }

    public void showAdminLoginMenu() {
        System.out.println("\nAdmin Sign In");

        while(true) {
            String email = readEmail();
            String password = readPassword();

            boolean success = adminSignInModel.login(email, password);
            if(success) {
                new AdminDashboard().showAdminDashboard();
                return;
            }
        }
    }

    private String readEmail() {
        while(true) {
            System.out.print("Enter email address : ");
            String email = scanner.nextLine();

            String errorInEmail = adminSignInModel.validateEmail(email);
            if(errorInEmail == null) return email.trim();
            showMessage(errorInEmail);
        }
    }

    private String readPassword() {
        while(true) {
            System.out.print("Enter password : ");
            String password = scanner.nextLine();

            String errorInPassword = adminSignInModel.validatePassword(password);
            if(errorInPassword == null) return password;
            showMessage(errorInPassword);
        }
    }

    public void showMessage(String message) {
        System.out.println();
        System.out.println(message);
    }
}
