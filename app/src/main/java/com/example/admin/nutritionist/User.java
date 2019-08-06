package com.example.admin.nutritionist;

public class User {
    public String id;
    public String userName;
    public String email;
    public String password;
    public String weight;
    public String height;
    public String age;
    public String bmi;
    public String sex;
    public String diabetic;

    public User(String id, String userName, String email, String password, String weight, String height, String age, String sex, String diabetic, String bmi) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.sex = sex;
        this.diabetic = diabetic;
        this.bmi = bmi;
    }
}

