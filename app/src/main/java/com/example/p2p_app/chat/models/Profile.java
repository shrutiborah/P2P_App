package com.example.p2p_app.chat.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user")
    @Expose
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("user_avatar")
    @Expose
    private String userAvatar;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("education_institute")
    @Expose
    private String educationInstitute;
    @SerializedName("education_degree")
    @Expose
    private String educationDegree;
    @SerializedName("education_specialization")
    @Expose
    private String educationSpecialization;
    @SerializedName("education_fromdate")
    @Expose
    private String educationFromdate;
    @SerializedName("education_todate")
    @Expose
    private String educationTodate;
    @SerializedName("materials_unlocked")
    @Expose
    private List<Material> materialsUnlocked = null;
    @SerializedName("interests")
    @Expose
    private List<Interest> interests = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Object getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getEducationInstitute() {
        return educationInstitute;
    }

    public void setEducationInstitute(String educationInstitute) {
        this.educationInstitute = educationInstitute;
    }

    public String getEducationDegree() {
        return educationDegree;
    }

    public void setEducationDegree(String educationDegree) {
        this.educationDegree = educationDegree;
    }

    public String getEducationSpecialization() {
        return educationSpecialization;
    }

    public void setEducationSpecialization(String educationSpecialization) {
        this.educationSpecialization = educationSpecialization;
    }

    public Object getEducationFromdate() {
        return educationFromdate;
    }

    public void setEducationFromdate(String educationFromdate) {
        this.educationFromdate = educationFromdate;
    }

    public Object getEducationTodate() {
        return educationTodate;
    }

    public void setEducationTodate(String educationTodate) {
        this.educationTodate = educationTodate;
    }

    public List<Material> getMaterialsUnlocked() {
        return materialsUnlocked;
    }

    public void setMaterialsUnlocked(List<Material> materialsUnlocked) {
        this.materialsUnlocked = materialsUnlocked;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }

}