package com.example.workoutapp.model;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<User> users;
    private List<Workout> workouts;

    public Database() {
        this.users = users;
        this.workouts = new ArrayList<Workout>();
        this.workouts.add(new Workout("Cycling workout #1"));
        this.workouts.add(new Workout("Cycling workout #2"));
        this.workouts.add(new Workout("Cycling workout #3"));
    }

    public List<User> getUsers(){
        return users;
    }

    public List<Workout> getWorkouts(){
        return workouts;
    }
}
