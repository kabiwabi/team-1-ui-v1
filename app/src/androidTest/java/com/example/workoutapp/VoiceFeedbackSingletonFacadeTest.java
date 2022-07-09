package com.example.workoutapp;

import static org.mockito.Mockito.*;

import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;

import androidx.test.annotation.UiThreadTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class VoiceFeedbackSingletonFacadeTest extends TestCase {
    @Test
    @UiThreadTest
    public void speakMethodIsCalledFromWithinAudioOutput() {
        TextToSpeech textToSpeech = mock(TextToSpeech.class);

        VoiceFeedbackSingletonFacade.loadConfigurationSettings();
        VoiceFeedbackSingletonFacade.setTextToSpeech(textToSpeech);
        VoiceFeedbackSingletonFacade.outputAudio("Hi");

        verify(textToSpeech, times(1)).speak("Hi", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Test
    @UiThreadTest
    public void speakMethodCanBeCalledMultipleTimes() {
        TextToSpeech tts = mock(TextToSpeech.class);

        VoiceFeedbackSingletonFacade.loadConfigurationSettings();
        VoiceFeedbackSingletonFacade.setTextToSpeech(tts);
        VoiceFeedbackSingletonFacade.outputAudio("Hi");
        VoiceFeedbackSingletonFacade.outputAudio("Hi");
        VoiceFeedbackSingletonFacade.outputAudio("Hi");

        verify(tts, times(3)).speak("Hi", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Test
    @UiThreadTest
    public void workoutStartAudioIsCorrect() {
        TextToSpeech tts = mock(TextToSpeech.class);

        VoiceFeedbackSingletonFacade.loadConfigurationSettings();
        VoiceFeedbackSingletonFacade.setTextToSpeech(tts);
        VoiceFeedbackSingletonFacade.workoutStartAudio();

        verify(tts, times(1)).speak("Starting workout at an altitude of 28.5 kilometers", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Test
    @UiThreadTest
    public void workoutEndsAudioIsCorrect() {
        TextToSpeech tts = mock(TextToSpeech.class);

        VoiceFeedbackSingletonFacade.loadConfigurationSettings();
        VoiceFeedbackSingletonFacade.setTextToSpeech(tts);
        VoiceFeedbackSingletonFacade.workoutEndAudio();

        verify(tts, times(1)).speak("Ending workout. 40.5 kilometers have been travelled over the course of your workout", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Test
    @UiThreadTest
    public void splitStartAudioIsCorrect() {
        TextToSpeech tts = mock(TextToSpeech.class);

        VoiceFeedbackSingletonFacade.loadConfigurationSettings();
        VoiceFeedbackSingletonFacade.setTextToSpeech(tts);
        VoiceFeedbackSingletonFacade.splitStartAudio();

        verify(tts, times(1)).speak("Start split, 5", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Test
    public void splitEndAudioIsCorrect() {
        TextToSpeech tts = mock(TextToSpeech.class);

        VoiceFeedbackSingletonFacade.loadConfigurationSettings();
        VoiceFeedbackSingletonFacade.setTextToSpeech(tts);
        VoiceFeedbackSingletonFacade.splitEndAudio();

        verify(tts, times(1)).speak("Split finished - split starting in 5 minutes", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Test
    public void splitMidAudioIsCorrect() {
        TextToSpeech tts = mock(TextToSpeech.class);

        VoiceFeedbackSingletonFacade.loadConfigurationSettings();
        VoiceFeedbackSingletonFacade.setTextToSpeech(tts);
        VoiceFeedbackSingletonFacade.splitMidAudio();

        verify(tts, times(1)).speak("Mid split, 5", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Test
    @UiThreadTest
    public void batteryLowAudio() {
        TextToSpeech tts = mock(TextToSpeech.class);

        VoiceFeedbackSingletonFacade.loadConfigurationSettings();
        VoiceFeedbackSingletonFacade.setTextToSpeech(tts);
        VoiceFeedbackSingletonFacade.batteryLowAudio();

        verify(tts, times(1)).speak("Warning, you have 5.5 percent battery power left", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Test
    @UiThreadTest
    public void weatherAlertAudioIsCorrectIsCorrect() {
        TextToSpeech tts = mock(TextToSpeech.class);

        VoiceFeedbackSingletonFacade.loadConfigurationSettings();
        VoiceFeedbackSingletonFacade.setTextToSpeech(tts);
        VoiceFeedbackSingletonFacade.weatherAlertAudio();

        verify(tts, times(1)).speak("Emergency Weather Alert, Severe Thunderstorm at Montreal West until 9:00pm ETD", TextToSpeech.QUEUE_FLUSH, null);
    }


    @Test
    @UiThreadTest
    public void strayFromSetPathAlertAudioIsCorrect() {
        TextToSpeech tts = mock(TextToSpeech.class);

        VoiceFeedbackSingletonFacade.loadConfigurationSettings();
        VoiceFeedbackSingletonFacade.setTextToSpeech(tts);
        VoiceFeedbackSingletonFacade.strayFromSetPathAlertAudio();

        verify(tts, times(1)).speak("Warning, you've strayed from your set path", TextToSpeech.QUEUE_FLUSH, null);
    }



}