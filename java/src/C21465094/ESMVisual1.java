package C21465094;

import ie.tudublin.*;

public class ESMVisual1 {
    // Declare a reference to the main Combined class
    Combined ev1;

    // Constructor that takes an instance of Combined and sets the local reference to it
    public ESMVisual1(Combined ev1) {
        this.ev1 = ev1;
    }

    // Initialize variables
    float angle = 0; // Initial rotation angle
    int NUM_ROWS = 50; // Number of rows of hexagons
    int HEX_SIZE = 20; // Hexagon size
    float radius = 200; // Radius of the oval

    // Method that draws the visual
    public void draw() {
        // Set the background to black
        ev1.background(0);

        // Calculate the average amplitude of the audio player
        ev1.calculateAverageAmplitude();

        // Set the stroke color based on the amplitude
        ev1.stroke(ev1.map(ev1.getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        ev1.strokeWeight(2);

        // Set up lighting
        ev1.lights();
        ev1.directionalLight(255, 255, 255, 0, -1, -1);

        // Set up camera
        ev1.pushMatrix();
        ev1.camera(0, 0, 400, 0, 0, 0, 0, 1, 0);

        // Apply rotation
        ev1.rotateX(angle);
        ev1.rotateY(angle);

        // Loop through rows of hexagons
        for (int i = 0; i < NUM_ROWS; i++) {
            // Calculate the angle of the row
            float theta = ev1.map(i, 0, NUM_ROWS, 0, ev1.PI);
            // Calculate the radius of the row
            float rowRadius = radius * ev1.sin(theta);
            // Calculate the y position of the row
            float yPos = radius * ev1.cos(theta);
            // Calculate the number of hexagons in the row
            int numHexagons = ev1.round(ev1.TWO_PI * rowRadius / HEX_SIZE);
            // Calculate the width of each hexagon
            float hexWidth = ev1.TWO_PI * rowRadius / numHexagons;
            // Loop through each hexagon in the row
            for (int j = 0; j < numHexagons; j++) {
                // Calculate the x position of the hexagon
                float hexX = j * hexWidth + (i % 2) * hexWidth / 2 - numHexagons * hexWidth / 2;
                // Set the y and z position of the hexagon
                float hexY = yPos;
                float hexZ = 0;
                // Draw the hexagon
                hexagon(hexX, hexY, hexZ, HEX_SIZE * ev1.getSmoothedAmplitude());
            }
        }

        // Reset the transformation matrix
        ev1.popMatrix();

        // Increment the rotation angle
        angle += 0.01f;
    }

  void hexagon(float x, float y, float z, float size) {
    float h = size * ev1.sqrt(3) / 2;
    float[] fillColor = {ev1.map(y, -radius, radius, 0, 255), 255, 255};
    ev1.fill(fillColor[0], fillColor[1], fillColor[2]);
    ev1.beginShape();
    ev1.vertex(x + size, y, z);
    ev1.vertex(x + size / 2, y + h, z);
    ev1.vertex(x - size / 2, y + h, z);
    ev1.vertex(x - size, y, z);
    ev1.vertex(x - size / 2, y - h, z);
    ev1.vertex(x + size / 2, y - h, z);
    ev1.endShape(ev1.CLOSE);
  }
}

