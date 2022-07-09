package com.example.workoutapp.ui.workout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.workoutapp.model.Workout;

public class WorkoutViewModel extends ViewModel {

    private MutableLiveData<String> title;
    private Workout workout;

    public WorkoutViewModel() {
        title = new MutableLiveData<>();
        title.setValue("No Workout Selected");
    }

    public LiveData<String> getText() {
        return title;
    }

    public void setError(String error){
        title.setValue(error);
    }

    public void setWorkout(Workout workout){
        this.workout = workout;
        title.setValue(workout.getName());
    }
}