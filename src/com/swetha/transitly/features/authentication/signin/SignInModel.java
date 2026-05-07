package com.swetha.transitly.features.authentication.signin;

import com.swetha.transitly.data.dto.User;
import com.swetha.transitly.data.repository.TransitlyDB;

public class SignInModel {
    private final SignInView signInView;

    SignInModel(SignInView signInView) {
        this.signInView = signInView;
    }

    public boolean login(String email, String password) {
        User user = TransitlyDB.getInstance().getUserByEmail(email);

        if(!user.getPassword().equals(password)) {
            signInView.showMessage("Invalid password.");
            return false;
        }

        return signInView.onSuccessfulLogin(user.getName());
    }

    public boolean isEmailExists(String email) {
        return TransitlyDB.getInstance().getUserByEmail(email) != null;
    }
}
