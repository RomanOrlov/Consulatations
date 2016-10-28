package com.consulatations.backend.entity;

import java.util.Date;

public class Day {
    private Date day;
    private String radiosurgery;
    private String ochnoe;
    private String zaochnoe;
    private String oncology;

    public Day() {
    }

    public Day(String radiosurgery, String ochnoe, String zaochnoe, String oncology) {
        this.radiosurgery = radiosurgery;
        this.ochnoe = ochnoe;
        this.zaochnoe = zaochnoe;
        this.oncology = oncology;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getRadiosurgery() {
        return radiosurgery;
    }

    public void setRadiosurgery(String radiosurgery) {
        this.radiosurgery = radiosurgery;
    }

    public String getOchnoe() {
        return ochnoe;
    }

    public void setOchnoe(String ochnoe) {
        this.ochnoe = ochnoe;
    }

    public String getZaochnoe() {
        return zaochnoe;
    }

    public void setZaochnoe(String zaochnoe) {
        this.zaochnoe = zaochnoe;
    }

    public String getOncology() {
        return oncology;
    }

    public void setOncology(String oncology) {
        this.oncology = oncology;
    }
}
