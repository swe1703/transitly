package com.swetha.transitly.features.authentication.signup;

import com.swetha.transitly.data.dto.User;
import com.swetha.transitly.features.authentication.signin.SignInView;
import com.swetha.transitly.util.ConsoleInput;

import java.util.Scanner;

public class SignUpView {
    private final SignUpModel signUpModel;
    private final Scanner scanner;

    public SignUpView() {
        this.signUpModel = new SignUpModel(this);
        this.scanner = ConsoleInput.getScanner();
    }

    public boolean init() {
        return showSignUpMenu();
    }

    public boolean showSignUpMenu() {
        System.out.println("\nUser Sign Up");

        String name = readName();
        String email = readEmail();
        String password = readPassword();

        return signUpModel.registerUser(name, email, password);
    }

    private String readName() {
        while(true) {
            System.out.print("Enter your name : ");
            String userName = scanner.nextLine();

            String errorInName = signUpModel.validateName(userName);
            if(errorInName == null) return userName.trim();
            showMessage(errorInName);
        }
    }

    private String readEmail() {
        while(true) {
            System.out.print("Enter email address : ");
            String email = scanner.nextLine();

            String errorInEmail = SignUpModel.validateEmail(email);
            if(errorInEmail == null) return email.trim();
            showMessage(errorInEmail);
        }
    }

    private String readPassword() {
        while(true) {
            System.out.print("Enter password (Minimum 6 characters with at least a letter and a number) : ");
            String password = scanner.nextLine();

            String errorInPassword = SignUpModel.validatePassword(password);
            if(errorInPassword != null) {
                showMessage(errorInPassword);
                continue;
            }

            System.out.print("Confirm password : ");
            String confirmPassword = scanner.nextLine();
            String confirmError = signUpModel.validateConfirmPassword(password, confirmPassword);
            if(confirmError != null) {
                showMessage(confirmError);
                continue;
            }

            return password;
        }
    }

    public void onSuccessfulSignUp(User user) {
        System.out.println();
        System.out.println("Account has been created successfully.");
        System.out.println("Your username : " + user.getName());
        System.out.println("Please sign in to continue.");
    }

    public void showMessage(String message) {
        System.out.println(message);
        System.out.println();
    }
}
