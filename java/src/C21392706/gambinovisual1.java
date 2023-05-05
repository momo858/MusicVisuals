package C21392706;

import ie.tudublin.*;

public class gambinovisual1 {
    
    Combined gv1;
    float hexagonSize = 100; // Initial size of the hexagon
    float spinSpeed = 0.01f; // Speed of the hexagon spin
    int numPyramids = 20; // Number of pyramids
    Pyramid[] pyramids = new Pyramid[numPyramids]; // Array of pyramid objects

    public gambinovisual1(Combined gv1) {
        this.gv1 = gv1;
        
        // Instantiate Pyramid objects and store them in the pyramids array
        for (int i = 0; i < numPyramids; i++) {
            pyramids[i] = new Pyramid(gv1, gv1.random(gv1.width), gv1.random(-500, 0), gv1.random(50, 100));
        }
    }

    public void draw() {
        gv1.background(0); // Set background color to black

        // Draw falling pyramids
        for (Pyramid pyramid : pyramids) {
            pyramid.update(); // Update pyramid position
            pyramid.display(); // Display pyramid
        }

        // Translate to center of the screen
        gv1.translate(gv1.width/2, gv1.height/2);

        // Draw a spinning hexagon imploding into a spiral pattern based on the music
        float n = gv1.mySound.left.level() * 100; // Get the volume level of the left channel and multiply it by 100
        float size = hexagonSize + n; // Add the volume level to the initial size of the hexagon
        gv1.stroke(255); // Set stroke color to white
        gv1.fill(gv1.random(255), gv1.random(255), gv1.random(255)); // Set fill color to a random RGB value
        float r = size / 2; // Calculate radius of the hexagon

        gv1.pushMatrix(); // Push the current matrix onto the stack
        gv1.rotateY(gv1.millis() * spinSpeed); // Spin the hexagon based on the time elapsed
        for (int i = 0; i < 6; i++) {
            gv1.pushMatrix(); // Push the current matrix onto the stack
            gv1.rotateZ(i * gv1.TWO_PI / 6.0f); // Rotate the hexagon around the Z-axis by i*60 degrees
            gv1.translate(0, r, 0); // Translate the hexagon up by r pixels
            gv1.rotateX(gv1.TWO_PI / 3.0f); // Rotate the hexagon around the X-axis by 120 degrees
            gv1.beginShape(); // Start defining a shape
            for (int j = 0; j < 6; j++) {
                float x = r * gv1.cos(j * gv1.TWO_PI / 6.0f); // Calculate the x-coordinate of the vertex
                float y = r * gv1.sin(j * gv1.TWO_PI / 6.0f); // Calculate the y-coordinate of the vertex
                gv1.vertex(x, y); // Add a vertex to the shape at (x, y)
            }
            gv1.endShape(gv1.CLOSE); // End the shape
            gv1.popMatrix(); // Pop the previous matrix off the stack
        }
        gv1.popMatrix(); // Pop the initial matrix off the stack
    }

    public void keyPressed() {
        if (gv1.key == ' ')
        gv1.mySound.cue(0); // Rewind sound file to beginning
        gv1.mySound.play(); // Play sound file
    }
}

// Pyramid class
class Pyramid {
    Combined gv1;
    float x, y, z, baseSize, speed;

    Pyramid(Combined gv1, float x, float y, float baseSize) {
        this.gv1 = gv1;
        this.x = x;
        this.y = y;
        this.z = 0;
        this.baseSize = baseSize;
        this.speed = gv1.random(1, 5);
    }

    void update() {
        y += speed * (1 + gv1.mySound.left.level() * 5); // Update y position based on volume level
        if (y > gv1.height + baseSize) { // If pyramid goes offscreen
            y = -baseSize; // Reset y position to top of screen
            x = gv1.random(gv1.width); // Randomize x position
            speed = gv1.random(1, 5); // Randomize speed
        }
    }

    void display() {
        gv1 .pushMatrix(); // Push the current matrix onto the stack
        gv1.translate(x, y, z); // Translate the pyramid to its current position
        gv1.stroke(255); // Set stroke color to white
        gv1.fill(gv1.random(255), gv1.random(255), gv1.random(255), 150); // Set fill color to a random RGBA value

        gv1.beginShape(gv1.TRIANGLES); // Start defining a shape

        // Draw pyramid
        gv1.vertex(-baseSize / 2, baseSize / 2, 0); // Top left vertex
        gv1.vertex(baseSize / 2, baseSize / 2, 0); // Top right vertex
        gv1.vertex(0, 0, baseSize / 2); // Bottom vertex

        gv1.vertex(baseSize / 2, baseSize / 2, 0); // Top right vertex
        gv1.vertex(baseSize / 2, -baseSize / 2, 0); // Bottom right vertex
        gv1.vertex(0, 0, baseSize / 2); // Bottom vertex

        gv1.vertex(baseSize / 2, -baseSize / 2, 0); // Bottom right vertex
        gv1.vertex(-baseSize / 2, -baseSize / 2, 0); // Bottom left vertex
        gv1.vertex(0, 0, baseSize / 2); // Bottom vertex

        gv1.vertex(-baseSize / 2, -baseSize / 2, 0); // Bottom left vertex
        gv1.vertex(-baseSize / 2, baseSize / 2, 0); // Top left vertex
        gv1.vertex(0, 0, baseSize / 2); // Bottom vertex

        gv1.endShape(); // End the shape
        gv1.popMatrix(); // Pop the previous matrix off the stack
        }
    }
