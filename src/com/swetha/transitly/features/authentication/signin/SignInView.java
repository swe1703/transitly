package com.swetha.transitly.features.authentication.signin;

import com.swetha.transitly.features.user.UserDashboard;
import com.swetha.transitly.util.InputUtil;

import java.util.Scanner;

public class SignInView {
    private final SignInModel signInModel;
    private final Scanner scanner;

    public SignInView() {
        signInModel = new SignInModel(this);
        this.scanner = InputUtil.getScanner();
    }

    public void init() {
        showLoginMenu();
    }

    void showLoginMenu() {
        System.out.println("\nUser Sign In");

        while(true) {
            System.out.print("Enter email address : ");
            String email = scanner.nextLine().trim();

            if(!signInModel.isEmailExists(email)) {
                showMessage("Email address not found. Please sign up first.");
                return;
            }

            System.out.print("Enter password : ");
            String password = scanner.nextLine();

            boolean isLoggedIn = signInModel.login(email, password);
            if(isLoggedIn) return;
        }
    }

    boolean onSuccessfulLogin(String userName) {
        showMessage("Welcome " + userName + "!");
        new UserDashboard().showUserDashboard();
        return true;
    }

    void showMessage(String message) {
        System.out.println();
        System.out.println(message);
    }
}
