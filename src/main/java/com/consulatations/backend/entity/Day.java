package com.consulatations.backend.entity;

/**
 * Created by Роман on 07.02.2016.
 */
public class Day {
    private String day;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
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
