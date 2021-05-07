package com.example.Cs480Project.domain;

public class Nurse {
    int EmployeeId;
    String Gender;
    String name;
    int Age;
    String Address;
    String Phone;
    public Nurse(){

    }

    public Nurse(int employeeId, String gender, String name, int age, String address, String phone) {
        this.EmployeeId = employeeId;
        this.Gender = gender;
        this.name = name;
        this.Age = age;
        this.Address = address;
        this.Phone = phone;
    }



    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
