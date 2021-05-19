package com.animal.api.model;

public class MessageModel {

    private String Address1;
    private String animal_type;
    private String animal_gender;

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getAnimal_type() {
        return animal_type;
    }

    public void setAnimal_type(String animal_type) {
        this.animal_type = animal_type;
    }

    public String getAnimal_gender() {
        return animal_gender;
    }

    public void setAnimal_gender(String animal_gender) {
        this.animal_gender = animal_gender;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "Address1='" + Address1 + '\'' +
                ", animal_type='" + animal_type + '\'' +
                ", animal_gender='" + animal_gender + '\'' +
                '}';
    }

}
