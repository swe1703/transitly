package com.swetha.transitly.features.authentication.signin;

import com.swetha.transitly.features.authentication.signup.SignUpView;
import com.swetha.transitly.features.user.UserDashboard;
import com.swetha.transitly.util.ConsoleInput;

import java.util.Scanner;

public class SignInView {
    private final SignInModel signInModel;
    private final Scanner scanner;

    public SignInView() {
        signInModel = new SignInModel(this);
        this.scanner = ConsoleInput.getScanner();
    }

    public boolean init() {
        return showLoginMenu();
    }

    public boolean showLoginMenu() {
        System.out.println("\nUser Sign In");

        while(true) {
            System.out.print("Enter email address : ");
            String email = scanner.nextLine().trim();

            if(!signInModel.isEmailExists(email)) {
                showMessage("Email address not found. Please sign up first.");
                return false;
            }

            System.out.print("Enter password : ");
            String password = scanner.nextLine();

            boolean success = signInModel.login(email, password);
            if(success) return true;
        }
    }

    public void onSuccessfulLogin(String userName) {
        showMessage("Welcome " + userName + "!");
        new UserDashboard().showUserDashboard();
    }

    public void showMessage(String message) {
        System.out.println();
        System.out.println(message);
    }
}
