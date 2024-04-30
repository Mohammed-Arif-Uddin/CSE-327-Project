//
//package com.example.rentlink.Model;
//
//import android.widget.Button;
//
//import java.io.Serializable;
//
//public class Post implements Serializable {
//    private String imageUrl;
//    private String apartmentName;
//    private String rent;
//    private String size;
//    private Button viewPostButton; // Add viewPostButton member variable
//
//    public Post(String apartmentName, String rent, String size) {
//        this.apartmentName = apartmentName;
//        this.rent = rent;
//        this.size = size;
//    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }
//
//    public String getApartmentName() {
//        return apartmentName;
//    }
//
//    public void setApartmentName(String apartmentName) {
//        this.apartmentName = apartmentName;
//    }
//
//    public String getRent() {
//        return rent;
//    }
//
//    public void setRent(String rent) {
//        this.rent = rent;
//    }
//
//    public String getSize() {
//        return size;
//    }
//
//    public void setSize(String size) {
//        this.size = size;
//    }
//
//    public Button getViewPostButton() {
//        return viewPostButton;
//    }
//
//    public void setViewPostButton(Button viewPostButton) {
//        this.viewPostButton = viewPostButton;
//    }
//}
package com.example.rentlink.Model;

import java.io.Serializable;

public class Post implements Serializable {
    private String imageUrl;
    private String apartmentName;
    private String rent;
    private String size;
    private String firstname; // Add username member variable

    public Post(String apartmentName, String rent, String size) {
        this.apartmentName = apartmentName;
        this.rent = rent;
        this.size = size;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getfirstname() {
        return firstname;
    }

    public void setUsername(String firstname) {
        this.firstname = firstname;
    }
}
