package com.consulatations.backend.entity;

import java.util.Date;

public class Consultation {
    private Date time = new Date();
    private String displayedTime = "";
    private String name = "Фёдор";
    private String caseNum = "42/342";
    private String telephone = "+7 9244 1324";
    private String type = "";
    private String status = "Назначена";
    private String sex = "";

    public Consultation() {
    }

    public Consultation(Date time, String displayedTime, String name, String caseNum, String telephone, String status) {
        this.time = time;
        this.displayedTime = displayedTime;
        this.name = name;
        this.caseNum = caseNum;
        this.telephone = telephone;
        this.status = status;
    }

    public Consultation(String type, String name, String caseNum, String telephone, String status) {
        this.type = type;
        this.name = name;
        this.caseNum = caseNum;
        this.telephone = telephone;
        this.status = status;
    }

    public String getDisplayedTime() {
        return displayedTime;
    }

    public void setDisplayedTime(String displayedTime) {
        this.displayedTime = displayedTime;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(String caseNum) {
        this.caseNum = caseNum;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
