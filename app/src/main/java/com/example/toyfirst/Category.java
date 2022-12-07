package com.example.toyfirst;

public class Category {
    private int CID;
    private String CategoryName;
    private String CDetails;
    byte[] CImg;

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getCDetails() {
        return CDetails;
    }

    public void setCDetails(String CDetails) {
        this.CDetails = CDetails;
    }

    public byte[] getCImg() {
        return CImg;
    }

    public void setCImg(byte[] CImg) {
        this.CImg = CImg;
    }
}
