package C21421254;

import ddf.minim.*;
import ie.tudublin.*;

public class KYVisual1 {

    // Variables to control movement and animation
    float n1;
    float n2;
    
    Combined kv1; // Reference to the main program

    public KYVisual1(Combined kv1, AudioPlayer mySound) {
        this.kv1 = kv1;
    }

    public void draw() {
        float amplitude = kv1.mySound.mix.level(); // Get the audio level
        kv1.colorMode(kv1.HSB, 360, 100, 100); // Set the color mode
        
        // Use the audio level to change the background color
        kv1.background(kv1.map(amplitude, 0, 1, 0, 360), 100, 50);

        // Translate to the center of the screen
        kv1.translate(kv1.width / 2, kv1.height / 2);

        // Loop through each sample in the audio buffer
        for (int i = 0; i < kv1.mySound.bufferSize() - 1; i++) {
            // Calculate angles and positions based on the current frame and audio level
            float angle = kv1.sin(i + n1) * 50;
            float angle2 = kv1.sin(i + n2) * 500;

            float x = kv1.sin(kv1.radians(i)) * (angle2 + 30);
            float y = kv1.cos(kv1.radians(i)) * (angle2 + 30);

            float x3 = kv1.sin(kv1.radians(i)) * (500 / angle);
            float y3 = kv1.cos(kv1.radians(i)) * (500 / angle);

            // Draw circles and rectangles with colors based on the audio level
            kv1.fill(kv1.map(amplitude, 0, 1, 0, 360), 100, 100);
            kv1.ellipse(x, y, kv1.mySound.left.get(i) * 10, kv1.mySound.left.get(i) * 10);

            kv1.fill(kv1.map(amplitude, 0, 1, 0, 360), 50, 100);
            kv1.rect(x3, y3, kv1.mySound.left.get(i) * 20, kv1.mySound.left.get(i) * 10);

            kv1.fill(kv1.map(amplitude, 0, 1, 0, 360), 100, 100);
            kv1.rect(x, y, kv1.mySound.right.get(i) * 10, kv1.mySound.left.get(i) * 10);

            kv1.fill(kv1.map(amplitude, 0, 1, 0, 360), 100, 100);
            kv1.rect(x3, y3, kv1.mySound.right.get(i) * 10, kv1.mySound.right.get(i) * 20);
        }

        // Increment the movement variables for the next frame
        n1 += 0.008;
        n2 += 0.04;
    }
}
