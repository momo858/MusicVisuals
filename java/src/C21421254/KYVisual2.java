package C21421254;

import ie.tudublin.*;

// KYVisual2 is a class that generates a visual representation of audio data.
public class KYVisual2 {
    
    // numStars is the number of asteroids that will be displayed.
    int numStars = 200;
    // stars is an array of Star objects.
    Star[] stars = new Star[numStars];
    // kv2 is an instance of the Combined class from the processing library.
    Combined kv2;

    // Constructor for KYVisual2 class.
    public KYVisual2(Combined kv2) {
        // Set kv2 instance to the one passed in the constructor.
        this.kv2 = kv2;
        // Create a new Star object for each element of the stars array.
        for (int i = 0; i < numStars; i++) {
            stars[i] = new Star();
        }
    }

    // draw method generates the audio visualization.
    public void draw() {
        // Set the background to black.
        kv2.background(0);
        // Center the display.
        kv2.translate(kv2.width / 2, kv2.height / 2);

        // Draw asteroids
        for (int i = 0; i < numStars; i++) {
            // Update the position of the asteroid.
            stars[i].update();
            // Display the asteroid.
            stars[i].display();
        }

        // Draw exploding spheres reacting to the music
        // Get the audio level.
        float audioLevel = kv2.mySound.mix.level();
        // Calculate the number of spheres based on the audio level.
        int numSpheres = (int) (kv2.map(audioLevel, 0, 1, 1, 10));
        // Calculate the size of the spheres based on the audio level.
        float sphereSize = kv2.map(audioLevel, 0, 1, 50, 200);

        for (int i = 0; i < numSpheres; i++) {
            kv2.pushMatrix();
            // Set the color of the sphere based on the index of the sphere in the loop.
            float hue = kv2.map(i, 0, numSpheres, 0, 255);
            kv2.colorMode(kv2.HSB, 255);
            kv2.stroke(hue, 255, 255);
            kv2.fill(hue, 255, 255, 127);
            // Set the position of the sphere randomly within the display area.
            float x = kv2.random(-kv2.width / 2 + sphereSize / 2, kv2.width / 2 - sphereSize / 2);
            float y = kv2.random(-kv2.height / 2 + sphereSize / 2, kv2.height / 2 - sphereSize / 2);
            kv2.translate(x, y);
            // Draw the sphere.
            kv2.sphere(sphereSize);
            kv2.popMatrix();
        }
    }

    // Star class is a nested class that defines the behavior of asteroids.
    class Star {
        // x, y, and z are the coordinates of the asteroid.
        float x, y, z;

        // Constructor for Star class.
        Star() {
            // Set the initial position of the asteroid randomly within the display area.
            x = kv2.random(-kv2.width, kv2.width);
            y = kv2.random(-kv2.height, kv2.height);
            z = kv2.random(kv2.width);
        }

        // Update method updates the position of the asteroid.
        void update() {
            // Move the asteroid towards the
                    // camera based on the audio level.
            z -= kv2.mySound.mix.level() * 50;
            // If the asteroid has moved off the screen, reset its position to a random point on the screen.
            if (z < 1) {
            z = kv2.width;
            x = kv2.random(-kv2.width, kv2.width);
            y = kv2.random(-kv2.height, kv2.height);
            }
        }

            // Display method displays the asteroid on the screen.
    void display() {
        // Calculate the screen coordinates of the asteroid.
        float sx = kv2.map(x / z, 0, 1, 0, kv2.width);
        float sy = kv2.map(y / z, 0, 1, 0, kv2.height);
        float r = kv2.map(z, 0, kv2.width, 16, 0);
        // Calculate the hue of the asteroid based on its x-coordinate.
        float hue = kv2.map(sx, 0, kv2.width, 0, 255);

        // Set the color mode to HSB.
        kv2.colorMode(kv2.HSB, 255);
        // Set the stroke color based on the hue of the asteroid.
        kv2.stroke(hue, 255, 255);
        kv2.strokeWeight(2);
        // Draw the asteroid.
        drawAsteroid(sx, sy, r);
        // Reset the color mode to RGB.
        kv2.colorMode(kv2.RGB, 255);
    }

    // drawAsteroid method draws an asteroid at the given position and size.
    void drawAsteroid(float x, float y, float r) {
        // numPoints is the number of vertices in the asteroid.
        int numPoints = 10;
        // angle is the angle between each vertex of the asteroid.
        float angle = 2 * kv2.PI / numPoints;
        kv2.pushMatrix();
        kv2.translate(x, y);
        kv2.beginShape();
        // Create each vertex of the asteroid.
        for (int i = 0; i < numPoints; i++) {
            float a = angle * i;
            float d = r * (kv2.random(0.8f, 1.2f));
            float px = d * kv2.cos(a);
            float py = d * kv2.sin(a);
            kv2.vertex(px, py);
        }
        kv2.endShape(kv2.CLOSE);
        kv2.popMatrix();
    }
}
}