package com.swetha.transitly.features.authentication.signup;

import com.swetha.transitly.data.dto.User;
import com.swetha.transitly.data.repository.TransitlyDB;
import com.swetha.transitly.features.authentication.signin.SignInView;

import java.util.regex.Pattern;

class SignUpModel {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)+$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d).{6,}$");
    private final SignUpView signUpView;

    SignUpModel(SignUpView signUpView) {
        this.signUpView = signUpView;
    }

    String validateName(String userName) {
        if(userName == null || userName.trim().isEmpty()) {
            return "Name cannot be empty.";
        }

        return null;
    }

    String validateEmail(String email) {
        if(email == null || email.trim().isEmpty()) {
            return "Email cannot be empty.";
        }

        String trimmedEmail = email.trim();
        if(!EMAIL_PATTERN.matcher(trimmedEmail).matches()) {
            return "Enter a valid email address.";
        }

        if(TransitlyDB.getInstance().getUserByEmail(trimmedEmail) != null) {
            return "This email is already registered.";
        }

        return null;
    }

    String validatePassword(String password) {
        if(password == null || password.isEmpty()) {
            return "Password cannot be empty.";
        }

        if(!PASSWORD_PATTERN.matcher(password).matches()) {
            return "Password must be at least 6 characters and include at least one letter and one number.";
        }

        return null;
    }

    String validateConfirmPassword(String password, String confirmPassword) {
        if(confirmPassword == null || !confirmPassword.equals(password)) {
            return "Passwords do not match.";
        }

        return null;
    }

    void registerUser(String name, String email, String password) {
        User user = new User();

        user.setName(name.trim());
        user.setEmail(email.trim());
        user.setPassword(password);

        User userSaved = TransitlyDB.getInstance().addUser(user);
        if(userSaved == null) {
            signUpView.showMessage("Could not create account. Please try again.");
            return;
        }

        signUpView.onSuccessfulSignUp(userSaved);
    }
}
