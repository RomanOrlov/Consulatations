package com.consulatations;

/**
 * Created by Роман on 31.01.2016.
 */
public class Patient {
    private String time = "10-20";
    private String name = "Фёдор";
    private String caseNum = "42/342";
    private String telephone  = "+7 9244 1324";
    private String status = "Назначена";

    public Patient() {
    }

    public Patient(String time, String name, String caseNum, String telephone, String status) {
        this.time = time;
        this.name = name;
        this.caseNum = caseNum;
        this.telephone = telephone;
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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
}
