package com.example.workoutapp;

import android.speech.tts.TextToSpeech;

import java.util.Locale;
import java.sql.Timestamp;

public final class VoiceFeedbackSingletonFacade {

    private static TextToSpeech tts;
    public static boolean ttsIsSetup = false;

    // Configuration file related
    private static boolean workoutStartAudioShouldPlay;
    private static boolean workoutEndAudioShouldPlay;
    private static boolean splitStartAudioShouldPlay;
    private static boolean splitMidAudioShouldPlay;
    private static boolean splitEndAudioShouldPlay;
    private static boolean batteryLowAudioShouldPLay;
    private static boolean weatherAlertAudioShouldPlay;
    private static boolean strayFromSetPathAlertAudioShouldPlay;
    //TODO: add more of these - and maybe rethink whether we want them to be just random booleans or something else

    //TODO: maybe there's a better of making sure that "tts" is defined than "tts == null || !ttsIsSetup" guards everywhere?
    private VoiceFeedbackSingletonFacade() { }

    public static void setTextToSpeech(TextToSpeech textToSpeech) {
        tts = textToSpeech;
    }

    public static void outputAudio(String message) {
        if (tts == null || !ttsIsSetup) {
            return;
        }
        tts.speak(message, TextToSpeech.QUEUE_FLUSH, null);
    }

    public static void workoutStartAudio() {
        if (tts == null || !ttsIsSetup) {
            return;
        }
        if(!workoutStartAudioShouldPlay) {
            return;
        }
        //TODO: get all Data necessary from the Data service (altitude, etc)
        double altitudeInKM = 28.5;

        String workoutStartMessage = String.format("Starting workout at an altitude of %.1f kilometers", altitudeInKM);
        outputAudio(workoutStartMessage);
    }

    public static void workoutEndAudio() {
        if (tts == null || !ttsIsSetup) {
            return;
        }
        if(!workoutEndAudioShouldPlay) {
            return;
        }
        //TODO: get all Data necessary from the Data service (altitude, etc)
        double totalDistanceInKM = 40.5;

        String workoutEndMessage = String.format("Ending workout. %.1f kilometers have been travelled over the course of your workout", totalDistanceInKM);
        outputAudio(workoutEndMessage);
    }

    public static void splitStartAudio() {
        if (tts == null || !ttsIsSetup) {
            return;
        }
        if(!splitStartAudioShouldPlay) {
            return;
        }
        //TODO: get all Data necessary from the Data service (altitude, etc)
        int someInformation = 5;

        String splitStartMessage = String.format("Start split, %d", someInformation);
        outputAudio(splitStartMessage);
    }

    public static void splitMidAudio() {
        if (tts == null || !ttsIsSetup) {
            return;
        }
        if(!splitMidAudioShouldPlay) {
            return;
        }
        //TODO: get all Data necessary from the Data service (altitude, etc)
        int someInformation = 5;

        String splitStartMessage = String.format("Mid split, %d", someInformation);
        outputAudio(splitStartMessage);
    }

    public static void splitEndAudio() {
        if (tts == null || !ttsIsSetup) {
            return;
        }
        if(!splitEndAudioShouldPlay) {
            return;
        }
        //TODO: get all Data necessary from the Data service (altitude, etc)
        int minutesUntilNextSplit = 5;

        String splitEndMessage = String.format("Split finished - split starting in %d minutes", minutesUntilNextSplit);
        outputAudio(splitEndMessage);
    }

    public static void batteryLowAudio() {
        if (tts == null || !ttsIsSetup) {
            return;
        }
        if(!batteryLowAudioShouldPLay) {
            return;
        }
        //TODO: get all Data necessary from the Data service (altitude, etc)
        double batteryPercentage = 5.5;

        String workoutStartMessage = String.format("Warning, you have %.1f percent battery power left", batteryPercentage);
        outputAudio(workoutStartMessage);
    }

    public static void weatherAlertAudio() {
        if (tts == null || !ttsIsSetup) {
            return;
        }
        if(!weatherAlertAudioShouldPlay) {
            return;
        }
        //TODO: get all Data necessary from the Weather Alert Service (level, category, warningTime, location, endTime, etc)
        String level="Severe";
        String weatherCategory="Thunderstorm";
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        String location="Montreal West";
        String endTime="9:00pm EDT";

        String weatherAlertMessage="Emergency Weather Alert, Severe Thunderstorm at Montreal West until 9:00pm ETD";

        outputAudio(weatherAlertMessage);
    }

    public static void strayFromSetPathAlertAudio() {
        if (tts == null || !ttsIsSetup) {
            return;
        }
        if(!strayFromSetPathAlertAudioShouldPlay) {
            return;
        }

        //TODO: get all Data necessary from the Data service (km away from path, direction to face to head back, etc)
        String strayFromSetPathMessage = String.format("Warning, you've strayed from your set path");
        outputAudio(strayFromSetPathMessage);
    }

    public static void loadConfigurationSettings() {
        // These will be loaded from a configuration file
        workoutStartAudioShouldPlay          = true;
        workoutEndAudioShouldPlay            = true;
        splitStartAudioShouldPlay            = true;
        splitMidAudioShouldPlay              = true;
        splitEndAudioShouldPlay              = true;
        batteryLowAudioShouldPLay            = true;
        weatherAlertAudioShouldPlay          = true;
        strayFromSetPathAlertAudioShouldPlay = true;
    }
}