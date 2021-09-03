package com.shopme.admin.Brand;

public class BrandNotFoundException extends Exception{
    public BrandNotFoundException(String message){
        super(message);
    }

    public BrandNotFoundException() {
    }
}
