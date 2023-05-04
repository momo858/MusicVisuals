

import ddf.minim.*;
import ddf.minim.analysis.FFT;
import ie.tudublin.*;

public class gambinovisual1 extends Visual{
    
    AudioPlayer mySound;
    AudioPlayer player;
    FFT fft;
    Minim m;
    int x; // Used to make the circle spin
    int radius = 200; // Radius in pixels of the circle


    public void setup() {
        noCursor();
        smooth();
        frameRate(60);
        m = new Minim(this);
        mySound = m.loadFile("heroplanet.mp3");
        mySound.play();
    }

    public void settings() {
        size(800, 600, P3D);
    }

    public void draw(){
        translate(width/2, height/2);
        background(0);
        player = mySound;
        fft = new FFT(mySound.bufferSize(), mySound.sampleRate());
        
        // Create circle interior
        noStroke();
        fill(200, 0, 0);
        pushMatrix();
        rotate(radians(frameCount/2));
        rect(-60, -60, 120, 120);
        popMatrix();
        fill(255, 0, 0);
        pushMatrix();
        rotate(radians(frameCount*2));
        rect(-50, -50, 100, 100);
        popMatrix();
        fill(0, 0, 255);
        pushMatrix();
        rotate(radians(frameCount*3));
        rect(-40, -40, 80, 80);
        popMatrix();
        if(player.isPlaying()) x += 2; // Circle only rotates while music is playing
    
        // Audio Visualization
        fft.forward(player.mix);
        float bands = fft.specSize();
        for(int i = 0; i < bands*2; i++){
            // Starting positions of line
            float start_x = radius*cos(PI*(i+x)/bands);
            float start_y = radius*sin(PI*(i+x)/bands);
            
            // Draw line based on sound
            stroke(255);
            strokeWeight(5);
            if (i < bands){
                // Line based on band length
                line(start_x, start_y, start_x + fft.getBand(i)*7*cos(PI*(i+x)/bands), start_y + fft.getBand(i)*7*sin(PI*(i+x)/bands));
            } else {
                // Line based on frequency
                line(start_x, start_y, start_x + fft.getFreq(i)*5*cos(PI*(i+x)/bands), start_y + fft.getFreq(i)*5*sin(PI*(i+x)/bands));
            }
        }
        // Draw background circles based on volume
        float vol = player.mix.level();
        noFill();
        strokeWeight(2);
        stroke(255, 255, 255);
        ellipse(0, 0, vol*500, vol*500);
        stroke(255, 0, 0);
        ellipse(0, 0, vol*1000, vol*1000);
        stroke(0, 0, 255);
        ellipse(0, 0, vol*1500, vol*1500);
    }
}