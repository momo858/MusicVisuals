package ie.tudublin;

import ddf.minim.*;
import processing.core.*;

public class KYVisual2 extends Visual {

    Minim minim;
    AudioPlayer mySound;
    int numStars = 200;
    Star[] stars = new Star[numStars];

    public void setup() {
        noCursor();
        smooth();
        background(0);
        frameRate(60);

        for (int i = 0; i < numStars; i++) {
            stars[i] = new Star();
        }

        minim = new Minim(this);
        mySound = minim.loadFile("rocky.mp3");
        mySound.play();
    }

    public void settings() {
        size(800, 600, P3D);
    }

    public void draw() {
        background(0);
        translate(width / 2, height / 2);

        // Draw stars
        for (int i = 0; i < numStars; i++) {
            stars[i].update();
            stars[i].display();
        }

        // Draw cubes reacting to the music
        float audioLevel = mySound.mix.level();
        int numCubes = (int) (map(audioLevel, 0, 1, 1, 10));
        float cubeSize = map(audioLevel, 0, 1, 50, 200);

        for (int i = 0; i < numCubes; i++) {
            pushMatrix();
            float hue = map(i, 0, numCubes, 0, 255);
            colorMode(HSB, 255);
            stroke(hue, 255, 255);
            fill(hue, 255, 255, 127);
            float x = random(-width / 2 + cubeSize / 2, width / 2 - cubeSize / 2);
            float y = random(-height / 2 + cubeSize / 2, height / 2 - cubeSize / 2);
            translate(x, y);
            box(cubeSize);
            popMatrix();
        }
    }

    class Star {
        float x, y, z;

        Star() {
            x = random(-width, width);
            y = random(-height, height);
            z = random(width);
        }

        void update() {
            z -= mySound.mix.level() * 50;
            if (z < 1) {
                z = width;
                x = random(-width, width);
                y = random(-height, height);
            }
        }

        void display() {
            float sx = map(x / z, 0, 1, 0, width);
            float sy = map(y / z, 0, 1, 0, height);
            float r = map(z, 0, width, 16, 0);
            float hue = map(sx, 0, width, 0, 255);

            colorMode(HSB, 255);
            stroke(hue, 255, 255);
            strokeWeight(2);
            ellipse(sx, sy, r, r);
            colorMode(RGB, 255);
        }
    }
}