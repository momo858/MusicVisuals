package C21392706;

import ddf.minim.*; // Import the Minim package
import ddf.minim.analysis.FFT; // Import the FFT class from Minim
import ie.tudublin.*; // Import the ie.tudublin package

public class gambinovisual2 {
    FFT frequencyAnalyzer; // An instance of the FFT class
    Minim minimInstance; // An instance of the Minim class (unused)
    int rotationDegree; // Used to make the circle spin
    int circleRadius = 200; // Radius in pixels of the circle
    Combined gv2; // An instance of the Combined class 

    // Constructor that takes an instance of the Combined class as a parameter
    public gambinovisual2(Combined gv2){
        this.gv2 = gv2;
    }

    // Method that is called to draw the visualization
    public void draw(){
        gv2.translate(gv2.width/2, gv2.height/2); // Translate the origin to the center of the canvas
        gv2.background(0); // Fill the background with black

        frequencyAnalyzer = new FFT(gv2.mySound.bufferSize(), gv2.mySound.sampleRate()); // Create a new FFT object with the sound buffer size and sample rate

        // Create circle interior
        gv2.noStroke(); // Disable stroke
        gv2.fill(200, 0, 0); // Set fill color to red
        gv2.pushMatrix(); // Save the current transformation matrix
        gv2.rotate(gv2.radians(gv2.frameCount/2)); // Rotate the circle based on frame count
        gv2.rect(-60, -60, 120, 120); // Draw a rectangle at (-60, -60) with a width and height of 120 pixels
        gv2.popMatrix(); // Restore the saved transformation matrix

        gv2.fill(255, 0, 0); // Set fill color to red
        gv2.pushMatrix(); // Save the current transformation matrix
        gv2.rotate(gv2.radians(gv2.frameCount*2)); // Rotate the circle based on frame count
        gv2.rect(-50, -50, 100, 100); // Draw a rectangle at (-50, -50) with a width and height of 100 pixels
        gv2.popMatrix(); // Restore the saved transformation matrix

        gv2.fill(0, 0, 255); // Set fill color to blue
        gv2.pushMatrix(); // Save the current transformation matrix
        gv2.rotate(gv2.radians(gv2.frameCount*3)); // Rotate the circle based on frame count
        gv2.rect(-40, -40, 80, 80); // Draw a rectangle at (-40, -40) with a width and height of 80 pixels
        gv2.popMatrix(); // Restore the saved transformation matrix

        if(gv2.mySound.isPlaying()) rotationDegree += 2; // Circle only rotates while music is playing

        // Audio Visualization
        frequencyAnalyzer.forward(gv2.mySound.mix); // Perform a forward FFT on the sound mix
        float bands = frequencyAnalyzer.specSize(); // Get the number of frequency bands in the FFT
        for(int i = 0; i < bands*2; i++){
            // Starting positions of line
            float start_x = circleRadius*gv2.cos(gv2.PI*(i+rotationDegree)/bands);
            float start_y = circleRadius*gv2.sin(gv2.PI*(i+rotationDegree)/bands);
        // Draw line based on sound
        gv2.stroke(255); // Set stroke color to white
        gv2.strokeWeight(5); // Set stroke weight to 5 pixels
        if (i < bands){
            // Line based on band length
            gv2.line(start_x, start_y, start_x + frequencyAnalyzer.getBand(i)*8*gv2.cos(gv2.PI*(i+rotationDegree)/bands), start_y + frequencyAnalyzer.getBand(i)*8*gv2.sin(gv2.PI*(i+rotationDegree)/bands));
        } else {
            // Line based on frequency
            gv2.line(start_x, start_y, start_x + frequencyAnalyzer.getFreq(i)*6*gv2.cos(gv2.PI*(i+rotationDegree)/bands), start_y + frequencyAnalyzer.getFreq(i)*6*gv2.sin(gv2.PI*(i+rotationDegree)/bands));
        }
    }

    // Draw background circles based on volume
    float vol = gv2.mySound.mix.level(); // Get the volume level of the sound mix
    gv2.noFill(); // Disable fill
    gv2.strokeWeight(2); // Set stroke weight to 2 pixels
    gv2.stroke(255, 255, 255); // Set stroke color to white
    gv2.ellipse(0, 0, vol*500, vol*500); // Draw an ellipse at the origin with a radius based on volume
    gv2.stroke(255, 0, 0); // Set stroke color to red
    gv2.ellipse(0, 0, vol*1000, vol*1000); // Draw an ellipse at the origin with a radius based on volume
    gv2.stroke(0, 0, 255); // Set stroke color to blue
    gv2.ellipse(0, 0, vol*1500, vol*1500); // Draw an ellipse at the origin with a radius based on volume
}
}