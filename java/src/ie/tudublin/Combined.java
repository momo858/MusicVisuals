package ie.tudublin;

import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.FFT;

public class Combined extends Visual{

    
    public void setup() {
        colorMode(HSB);
        //noCursor();
        frameRate(60);
        startMinim();
        loadAudio("heroplanet.mp3");
    }
    public void setup() {
        noCursor();
        colorMode(HSB);
        smooth();
        background (0);
        frameRate(60);
        loadAudio("heroplanet.mp3");
    }


}
