package com.example.collegealumni.util;

import android.net.Uri;
import android.util.Patterns;


public class FormValidation {

    public String validateRegistration(String name, String email, String pass) {

        if( name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            return "Fill every details";
        }

        if( ! Patterns.EMAIL_ADDRESS.matcher( email).matches()) {
            return "Invalid Email Address";
        }

        if( pass.length() < 8) {
            return "Password length should be greater than or equal to 8";
        }

        return "Register Successful";
    }

    public String validateLogin(String email, String pass) {
        if( email.isEmpty() || pass.isEmpty()) {
            return "Fill every details";
        }

        if( ! Patterns.EMAIL_ADDRESS.matcher( email).matches()) {
            return "Invalid Email Address";
        }

        if( pass.length() < 8) {
            return "Password length should be greater than or equal to 8";
        }

        return "Login Successful";
    }

    public String validatePost(String title, String caption, Uri imageURI) {
        if( title.isEmpty() || caption.isEmpty()) {
            return "Fill every details";
        }

        if( imageURI == null) {
            return "Select Image to post";
        }

        return "Posted Successfully";
    }

    public String validateSurvey( Uri imageURI) {

        if( imageURI == null) {
            return "Select Image to post";
        }

        return "Profile Updated Successfully";
    }
}
