package com.example.mc;

import android.graphics.Bitmap;

public class Good {
    private String name;
    private String company;
    private int image;
    private String description;
    private String price;

    public Good(String name, String company, int image, String description, String price){

        this.name=name;
        this.company = company;
        this.image = image;
        this.description = description;
        this.price = price;
    }



    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getImage() {
        return this.image;
    }

    public void setImage(int image) {
        this.image = image;
    }
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
}

