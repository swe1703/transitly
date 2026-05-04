package com.swetha.transitly.features.profile;

public class ProfileView {
    private final ProfileModel profileModel;

    public ProfileView() {
        profileModel = new ProfileModel(this);
    }

    public void init() {
    }

    public void showProfileMenu() {
    }

    public void viewProfile() {
        profileModel.getProfileDetails();
    }

    public void updateProfile() {
        profileModel.updateProfile();
    }

    public void changePassword() {
        profileModel.changePassword();
    }

    public void deleteAccount() {
        profileModel.deleteAccount();
    }
}
