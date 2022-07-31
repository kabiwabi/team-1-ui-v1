package com.example.workoutapp;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.os.CountDownTimer;

import com.example.workoutapp.model.Controller;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.workoutapp.databinding.ActivityMainBinding;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 600000;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private boolean mTimerRunning;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private Button button;
    private TextView textView;
    private Button stop_button;
    private Button settings_button;
    public long countdown = 6000;
    CountDownTimer start;
    public TextView unit_measurement;
    private Controller controller;
    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;
    boolean timerStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        controller = new Controller();

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_workout, R.id.nav_settings)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        timer = new Timer();

        // Setup global TextToSpeech instance
        VoiceFeedbackSingletonFacade.loadConfigurationSettings();
        VoiceFeedbackSingletonFacade.setTextToSpeech(new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    VoiceFeedbackSingletonFacade.ttsIsSetup = true;
                }
            }
        }));
    }

    //WANT TO GET IT TO WORK HERE
    public Controller getController(){
        return controller;
    }

    public void click_Start_Stop(View view)
    {
        button = findViewById(R.id.button_start_stop);
        textView = findViewById(R.id.text_durationCounter);
        stop_button = findViewById(R.id.button_pause);

        if(timerStarted == false)
        {
            timerStarted = true;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    startCountdown();
                }

            });
            button.setText("Pause");
            startTimer();

            boolean workoutInProgress = (time != 0);
            if(workoutInProgress) {
                VoiceFeedbackSingletonFacade.splitStartAudio();
            } else {
                VoiceFeedbackSingletonFacade.workoutStartAudio();
            }
        }
        else
        {
            timerStarted = false;
            button.setText("Start");
            timerTask.cancel();

            VoiceFeedbackSingletonFacade.splitEndAudio();
        }

        stop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setText("Start");
                resetTimer();

                VoiceFeedbackSingletonFacade.workoutEndAudio();
            }
        });
    }

    private void startTimer() {
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        time++;
                        textView.setText(getTimerText());
                    }
                });
            }

        };
        timer.scheduleAtFixedRate(timerTask, countdown ,1000);
    }


    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        button.setText("Start");
//        pause_button.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        if(timerTask != null)
        {
            timerTask.cancel();
            time = 0.0;
            timerStarted = false;
            textView.setText(formatTime(0,0,0));

        }
    }

    private String getTimerText()
    {
        int rounded = (int) Math.round(time);

        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600);

        return formatTime(seconds, minutes, hours);
    }

    private String formatTime(int seconds, int minutes, int hours)
    {
        return String.format("%02d",hours) + ":" + String.format("%02d",minutes) + ":" + String.format("%02d",seconds);
    }

    public void click_settings(View view){
        settings_button = findViewById(R.id.button_settings);
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment_content_main);
        NavController navController = navHostFragment.getNavController();
        navController.navigate(R.id.action_nav_workout_to_nav_settings);
    }

    public void startCountdown(){
        TextView textView = findViewById(R.id.textView2);
        textView.setVisibility(View.VISIBLE);
        start = new CountDownTimer(countdown, 1000) {

            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished/1000 == 0){
                    textView.setText("Start!");
                }
                else {
                    textView.setText("" + (millisUntilFinished / 1000));
                }
            }
            public void onFinish() {
                textView.setVisibility(View.GONE);
            }
        }.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}