package com.example.workoutapp.model;

import java.util.List;

public class Controller {
    private Database db;

    public Controller() {
        this.db = new Database();
    }

    public List<Workout> getWorkouts(){
        return db.getWorkouts();
    }
}
