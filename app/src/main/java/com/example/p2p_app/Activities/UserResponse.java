package com.example.p2p_app.Activities;

import java.io.Serializable;

public class UserResponse implements Serializable {


    private int id, points;
    private String user,created,modified,gender,user_avatar,about,education_institute,education_degree,
            education_specialization,education_fromdate, education_todate;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

//    public int getMaterials_claimed() {
//        return materials_claimed;
//    }
//
//    public void setMaterials_claimed(int materials_claimed) {
//        this.materials_claimed = materials_claimed;
//    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getEducation_institute() {
        return education_institute;
    }

    public void setEducation_institute(String education_institute) {
        this.education_institute = education_institute;
    }

    public String getEducation_degree() {
        return education_degree;
    }

    public void setEducation_degree(String education_degree) {
        this.education_degree = education_degree;
    }

    public String getEducation_specialization() {
        return education_specialization;
    }

    public void setEducation_specialization(String education_specialization) {
        this.education_specialization = education_specialization;
    }

    public String getEducation_fromdate() {
        return education_fromdate;
    }

    public void setEducation_fromdate(String education_fromdate) {
        this.education_fromdate = education_fromdate;
    }

    public String getEducation_todate() {
        return education_todate;
    }

    public void setEducation_todate(String education_todate) {
        this.education_todate = education_todate;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", points=" + points +
//                ", materials_claimed=" + materials_claimed +
                ", user='" + user + '\'' +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                ", gender='" + gender + '\'' +
                ", user_avatar='" + user_avatar + '\'' +
                ", about='" + about + '\'' +
                ", education_institute='" + education_institute + '\'' +
                ", education_degree='" + education_degree + '\'' +
                ", education_specialization='" + education_specialization + '\'' +
                ", education_fromdate='" + education_fromdate + '\'' +
                ", education_todate='" + education_todate + '\'' +
                '}';
    }
}
