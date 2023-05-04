package ie.tudublin;

import ddf.minim.*;
import ddf.minim.signals.*;
import processing.core.*;

public class KYVisual1 extends Visual {

    Minim minim;
    AudioPlayer mySound;
    float n4;
    float n6;

    public void settings() {
        size(800, 600, P3D);
    }

    public void setup() {
        noCursor();
        smooth();
        background(0);
        frameRate(60);

        minim = new Minim(this);
        mySound = minim.loadFile("rocky.mp3");
        mySound.play();
    }

    public void draw() {
        float amplitude = mySound.mix.level();
        colorMode(HSB, 360, 100, 100);
        background(map(amplitude, 0, 1, 0, 360), 100, 50);

        translate(width / 2, height / 2);

        for (int i = 0; i < mySound.bufferSize() - 1; i++) {
            float angle = sin(i + n4) * 50;
            float angle2 = sin(i + n6) * 500;

            float x = sin(radians(i)) * (angle2 + 30);
            float y = cos(radians(i)) * (angle2 + 30);

            float x3 = sin(radians(i)) * (500 / angle);
            float y3 = cos(radians(i)) * (500 / angle);

            fill(map(amplitude, 0, 1, 0, 360), 100, 100); // Set color based on amplitude
            ellipse(x, y, mySound.left.get(i) * 10, mySound.left.get(i) * 10);

            fill(map(amplitude, 0, 1, 0, 360), 50, 100);
            rect(x3, y3, mySound.left.get(i) * 20, mySound.left.get(i) * 10);

            fill(map(amplitude, 0, 1, 0, 360), 100, 100);
            rect(x, y, mySound.right.get(i) * 10, mySound.left.get(i) * 10);

            fill(map(amplitude, 0, 1, 0, 360), 100, 100);
            rect(x3, y3, mySound.right.get(i) * 10, mySound.right.get(i) * 20);
        }

        n4 += 0.008;
        n6 += 0.04;
    }

    public void stop() {
        mySound.close();
        minim.stop();
        super.stop();
    }
}
