package com.swetha.transitly.features.authentication.adminsignin;

import com.swetha.transitly.data.repository.TransitlyDB;

class AdminSignInModel {
    private final AdminSignInView adminSignInView;

    AdminSignInModel(AdminSignInView adminSignInView) {
        this.adminSignInView = adminSignInView;
    }

    boolean login(String email, String password) {
        TransitlyDB transitlyDB = TransitlyDB.getInstance();

        if(transitlyDB.getAdminEmail().equalsIgnoreCase(email) && transitlyDB.getAdminPassword().equalsIgnoreCase(password)) {
            adminSignInView.showMessage("Welcome Admin!");
            return true;
        }

        adminSignInView.showMessage("Invalid admin credentials.");
        return false;
    }

    String validateEmail(String email) {
        if(email == null || email.trim().isEmpty()) {
            return "Email cannot be empty.";
        }

        return null;
    }

    String validatePassword(String password) {
        if(password == null || password.isEmpty()) {
            return "Password cannot be empty.";
        }

        return null;
    }
}
