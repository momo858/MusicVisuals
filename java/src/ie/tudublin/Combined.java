package ie.tudublin;

import ddf.minim.*;
import C21421254.*;
import C21465094.*;
import C21392706.*;

public class Combined extends Visual {

    int visual = 0; // Stores the currently selected visual
    int m = 0; // Stores the elapsed time in seconds
    public AudioPlayer mySound; // Minim audio player

    // Declare the visual variables without initializing them
    KYVisual1 kv1;
    KYVisual2 kv2;
    gambinovisual1 gv1;
    gambinovisual2 gv2;
    ESMVisual1 ev1;
    ESMVisual2 ev2;

    public void settings() {
        size(1920, 1080, P3D); // Set the size and renderer of the sketch
        fullScreen(SPAN);
    }

    public void setup() {
        colorMode(HSB); // Set the color mode of the sketch
        frameRate(60); // Set the frame rate of the sketch

        // Initialize Minim and load the audio file
        startMinim();
        loadAudio("rockyclean.mp3");

        // Initialize the visuals after loading the audio
        initializeVisuals();

        // Start playing the audio
        getAudioPlayer().play();
        smooth(); 
        
        minim = new Minim(this);
        mySound = minim.loadFile("rockyclean.mp3");
        mySound.play();
    }

    public void initializeVisuals() {
        // Initialize each visual and pass a reference to this sketch and the AudioPlayer
        kv1 = new KYVisual1(this, getAudioPlayer());
        kv2 = new KYVisual2(this);
        gv1 = new gambinovisual1(this);
        gv2 = new gambinovisual2(this);
        ev1 = new ESMVisual1(this);
        ev2 = new ESMVisual2(this);
    }

    public void keyPressed() {
        // Select the visual based on the key pressed
        if (keyCode == 49) {
            visual = 1;
        } else if (keyCode == 50) {
            visual = 2;
        } else if (keyCode == 51) {
            visual = 3;
        } else if (keyCode == 52) {
            visual = 4;
        } else if (keyCode == 53) {
            visual = 5;
        } else if (keyCode == 54) {
            visual = 6;
        } else if (keyCode == 55) { // Exit the sketch if the number 7 is pressed
            exit();
        }
    }

    public void draw() {
        System.out.println(keyCode); // Print the ASCII code of the key pressed to the console

        background(0); // Set the background color to black
        calculateFrequencyBands(); // Calculate the frequency bands of the audio
        calculateAverageAmplitude(); // Calculate the average amplitude of the audio
        try {
            calculateFFT(); // Call this if you want to use FFT data
        } catch (VisualException e) {
            e.printStackTrace();
        }
        m = millis() / 1000; // Calculate the elapsed time in seconds

        // Draw the currently selected visual
        if (visual == 1) {
            gv1.draw();
        } else if (visual == 2) {
            gv2.draw();
        } else if (visual == 3) {
            ev1.draw();
        } else if (visual == 4) {
            ev2.draw();
        } else if (visual == 5) {
            kv1.draw();
        }  else if (visual == 6) {
            kv2.draw();
        }
    }
    }
