package com.example.workoutapp.ui.settings;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutapp.MainActivity;
import com.example.workoutapp.R;
import com.example.workoutapp.VoiceFeedbackSingletonFacade;
import com.example.workoutapp.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Switch dark_mode_switch = binding.settingswitch3;
        Switch auto_pause_enable = binding.settingswitch1;
        Switch text_to_speech = binding.settingswitch2;
        Spinner count_down_speed = binding.spinner1;
        Spinner unit_measurement = binding.spinner2;

        //set dark mode
        dark_mode_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        //set auto-pause
        auto_pause_enable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        //set text-to-speech
        text_to_speech.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    VoiceFeedbackSingletonFacade.ttsIsSetup = true;
                    VoiceFeedbackSingletonFacade.loadConfigurationSettings();
                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "text to speech enabled";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else{
                    VoiceFeedbackSingletonFacade.ttsIsSetup = false;
                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "text to speech disabled";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

        //set countdown speed
        count_down_speed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(id == 0){
                    ((MainActivity)getActivity()).countdown = 3000;
                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "countdown set to faster";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else if(id == 1){
                    ((MainActivity)getActivity()).countdown = 4000;
                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "countdown set to normal";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else if(id == 2){
                    ((MainActivity)getActivity()).countdown = 5000;
                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "countdown set to slower";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                ((MainActivity)getActivity()).countdown = 4000;
            }
        });

        //set unit measurement
        unit_measurement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(id == 0){
                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "unit set to metric";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else if(id == 1){
                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "unit set to imperial";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        final TextView textView = binding.textSettings;
        settingsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}