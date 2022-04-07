package com.example.ideaflower.classs;

public class Order {
    String mFlowerName,idflower;
    int imgFlower,quantity,price;

    public Order(String mFlowerName, int imgFlower, String idflower, int quantity, int price) {
        this.mFlowerName = mFlowerName;
        this.imgFlower = imgFlower;
        this.idflower = idflower;
        this.quantity = quantity;
        this.price = price;
    }

    public Order() {
    }

    public String getmFlowerName() {
        return mFlowerName;
    }

    public void setmFlowerName(String mFlowerName) {
        this.mFlowerName = mFlowerName;
    }

    public int getImgFlower() {
        return imgFlower;
    }

    public void setImgFlower(int imgFlower) {
        this.imgFlower = imgFlower;
    }

    public String getIdflower() {
        return idflower;
    }

    public void setIdflower(String idflower) {
        this.idflower = idflower;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
