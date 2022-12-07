package com.example.toyfirst;

public class Toy {
    private int TID;
    private String TName;
    private String TCategory;
    private String TPrice;
    byte[] TImg;

    public int getTID() {
        return TID;
    }

    public void setTID(int TID) {
        this.TID = TID;
    }

    public String getTName() {
        return TName;
    }

    public void setTName(String TName) {
        this.TName = TName;
    }

    public String getTCategory() {
        return TCategory;
    }

    public void setTCategory(String TCategory) {
        this.TCategory = TCategory;
    }

    public String getTPrice() {
        return TPrice;
    }

    public void setTPrice(String TPrice) {
        this.TPrice = TPrice;
    }

    public byte[] getTImg() {
        return TImg;
    }

    public void setTImg(byte[] TImg) {
        this.TImg = TImg;
    }
}
