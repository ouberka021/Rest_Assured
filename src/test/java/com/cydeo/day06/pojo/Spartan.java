package com.cydeo.day06.pojo;

public class Spartan {
//     "id": 15,
//      "name": "Meta",
//      "gender": "Female",
//       "phone": 1938695106
    private String id;
    private String name;
    private String gender;
    private String phone;

    // constructor
//    public Spartan(String id, String name, String gender, String phone) {
//        setId(id);
//        setName(name);
//        setGender(gender);
//        setPhone(phone);
//
//    }
    // getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    //toString
    @Override
    public String toString() {
        return "Spartan{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
