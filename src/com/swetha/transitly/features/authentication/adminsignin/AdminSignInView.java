package com.swetha.transitly.features.authentication.adminsignin;

import com.swetha.transitly.features.admin.AdminDashboard;
import com.swetha.transitly.util.InputUtil;

import java.util.Scanner;

public class AdminSignInView {
    private final AdminSignInModel adminSignInModel;
    private final Scanner scanner;

    public AdminSignInView() {
        adminSignInModel = new AdminSignInModel(this);
        scanner = InputUtil.getScanner();
    }

    public void init() {
        showAdminLoginMenu();
    }

     void showAdminLoginMenu() {
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

     String readEmail() {
        while(true) {
            System.out.print("Enter email address : ");
            String email = scanner.nextLine().trim();

            String emailError = adminSignInModel.validateEmail(email);
            if(emailError == null) return email;
            showMessage(emailError);
        }
    }

     String readPassword() {
        while(true) {
            System.out.print("Enter password : ");
            String password = scanner.nextLine();

            String passwordError = adminSignInModel.validatePassword(password);
            if(passwordError == null) return password;
            showMessage(passwordError);
        }
    }

    void showMessage(String message) {
        System.out.println();
        System.out.println(message);
    }
}
