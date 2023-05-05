package C21465094;
import ie.tudublin.*; // Import the ie.tudublin package

public class ESMVisual2 {
    float n; // Used to rotate the ellipses
    float n2; // Used to create motion in the visualization
    float n3; // Used to control the speed of motion
    float speed2; // Used to control the speed of motion
    
    Combined ev2; // An instance of the Combined class (assumed to be part of the ie.tudublin package)

    // Constructor that takes an instance of the Combined class as a parameter
    public ESMVisual2(Combined ev2){
        this.ev2 = ev2;
    }

    // Method that is called to draw the visualization
    public void draw() {
        ev2.fill(0, 20); // Fill the background with black with 20% opacity
        ev2.noStroke(); // Disable stroke
        ev2.rect(0, 0, ev2.width, ev2.height); // Draw a rectangle that covers the entire canvas
        ev2.translate(ev2.width / 2, ev2.height / 2); // Translate the origin to the center of the canvas

        // Loop through every sample in the sound buffer
        for (int i = 0; i < ev2.mySound.bufferSize() - 1; i++) {
            float leftLevel = ev2.mySound.left.level() * 20; // Get the amplitude of the left channel
            float hue = ev2.map(ev2.frameCount % 360, 0, 360, 0, 360); // Map the frame count to a hue value
            ev2.fill(hue, 250, 150); // Set the fill color based on the hue
            ev2.ellipse(i, i, leftLevel, leftLevel); // Draw an ellipse with the left channel amplitude as the radius
            ev2.rotateZ((float) (n * -ev2.PI / 3 * 0.05)); // Rotate the ellipse around the z-axis
            ev2.fill(400, 550, 23); // Set the fill color to a fixed value
        }

        // Add a new motion to the visualization
        n2 += 0.3; // Increment the motion variable
        float x = ev2.sin(n2) * ev2.width / 3; // Calculate the x-coordinate of the translation
        float y = ev2.cos(n2) * ev2.height / 3; // Calculate the y-coordinate of the translation
        ev2.translate(x, y); // Translate the origin by the calculated amount

        n += 0.08; // Increment the rotation variable
        n3 += speed2; // Increment the motion speed variable
    }
}

