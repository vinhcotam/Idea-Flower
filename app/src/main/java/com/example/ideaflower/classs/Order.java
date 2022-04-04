package com.example.ideaflower.classs;

public class Order {
    String mFlowerName;
    int imgFlower;

    public Order() {
    }

    public Order(String mFlowerName, int imgFlower) {
        this.mFlowerName = mFlowerName;
        this.imgFlower = imgFlower;
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
}
