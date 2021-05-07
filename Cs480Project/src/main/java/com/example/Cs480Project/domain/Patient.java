package com.example.Cs480Project.domain;

public class Patient {
    String name;
    int ssn;
    String race;
    String occupation;
    String gender;
    String medicalHistory;
    String address;
    String vaccine;
    public Patient(){

    }

    public Patient(String name, int ssn, String race, String occupation, String gender, String medicalHistory, String address,String vaccine) {
        this.name = name;
        this.ssn = ssn;
        this.race = race;
        this.occupation = occupation;
        this.gender = gender;
        this.medicalHistory = medicalHistory;
        this.address = address;
        this.vaccine = vaccine;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
