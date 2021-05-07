package com.example.Cs480Project.domain;

public class Vaccine {
    String name;
    String nameofCom;
    String texture;
    int numRequested;
    int storageAmount;
    int reservedAmount;

    public Vaccine(){

    }

    public Vaccine(String name, String nameofCom, String texture, int numRequested, int storageAmount, int reservedAmount) {
        this.name = name;
        this.nameofCom = nameofCom;
        this.texture = texture;
        this.numRequested = numRequested;
        this.storageAmount = storageAmount;
        this.reservedAmount = reservedAmount;
    }

    public String getName() {
        return name;
    }

    public String getNameofCom() {
        return nameofCom;
    }

    public String getTexture() {
        return texture;
    }

    public int getNumRequested() {
        return numRequested;
    }

    public int getStorageAmount() {
        return storageAmount;
    }

    public int getReservedAmount() {
        return reservedAmount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameofCom(String nameofCom) {
        this.nameofCom = nameofCom;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public void setNumRequested(int numRequested) {
        this.numRequested = numRequested;
    }

    public void setStorageAmount(int storageAmount) {
        this.storageAmount = storageAmount;
    }

    public void setReservedAmount(int reservedAmount) {
        this.reservedAmount = reservedAmount;
    }
}
