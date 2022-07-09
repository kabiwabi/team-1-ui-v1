package com.example.workoutapp.model;

import java.util.ArrayList;
import java.util.List;

public class Workout {
    private int id;
    private String name;
    private double duration;
    private double units;
    private List<Setting> settings;

    public Workout(String name, List<Setting> settings) {
        this.name = name;
        this.duration = 0;
        this.units = 0;
        this.settings = settings;
    }

    public Workout(String name) {
        this.name = name;
        this.duration = 0;
        this.units = 0;
        this.settings = new ArrayList<Setting>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getUnits() {
        return units;
    }

    public void setUnits(double units) {
        this.units = units;
    }
}
