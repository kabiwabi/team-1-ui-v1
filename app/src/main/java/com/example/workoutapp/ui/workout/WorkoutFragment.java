package com.example.workoutapp.ui.workout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutapp.MainActivity;
import com.example.workoutapp.databinding.FragmentWorkoutBinding;
import com.example.workoutapp.model.Controller;
import com.example.workoutapp.model.Workout;

import java.util.List;
import java.util.stream.Collectors;

public class WorkoutFragment extends Fragment {

    private FragmentWorkoutBinding binding;
    private Controller controller;

    private Workout workout;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        WorkoutViewModel workoutViewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);

        binding = FragmentWorkoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Get controller from MainActivity
        controller = ((MainActivity)getActivity()).getController();
        List<Workout> workouts = controller.getWorkouts();

        String nameList[] = new String[workouts.size()];
        nameList = workouts.stream().map(Workout::getName).collect(Collectors.toList()).toArray(nameList);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setTitle("Pick a Workout");
        builder.setItems(nameList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                workout = workouts.get(which);
                if(workout != null){
                    String workoutName = workout.getName();
                    if(!workoutName.isEmpty() && workoutName != null){
                        workoutViewModel.setWorkout(workout);
                    }
                    else{
                        workout = null;
                        workoutViewModel.setError("Error:: Workout was in an invalid state");
                    }
                }
                else{
                    workoutViewModel.setError("Error:: Workout is null");
                }
                final TextView textView = binding.textWorkout;
                workoutViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
            }
        });
        builder.show();

        final TextView textView = binding.textWorkout;
        workoutViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}