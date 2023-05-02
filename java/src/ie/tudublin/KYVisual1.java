package ie.tudublin;

import ddf.minim.*;
import ddf.minim.signals.*;
import processing.core.*;

public class KYVisual1 extends Visual{
    

Minim minim;
AudioPlayer mySound;
float n4;
float n6;
 

    public void setup() {
        noCursor();
        smooth();
        background (0);
        frameRate(60);
    

        minim = new Minim(this);
        mySound = minim.loadFile("rocky.mp3");    
        mySound.play();
    }

    public void settings() {
        size(800, 600, P3D);
    }
//MAIN SETUP
    public void draw() {
    
    fill(0,50);  
    noStroke();
    rect(0, 0, width, height);
    translate(width/2, height/2);
    
    for (int i = 0; i < mySound.bufferSize() - 1; i++) {
    
        float angle = sin(i+n4)* 5; 
        float angle2 = sin(i+n6)* 700; 
    
        float x = sin(radians(i))*(angle2+30); 
        float y = cos(radians(i))*(angle2+30);
    
        float x3 = sin(radians(i))*(500/angle); 
        float y3 = cos(radians(i))*(500/angle);
    
        fill (255, 255, 0); //yellow
        ellipse(x, y, mySound.left.get(i)*10, mySound.left.get(i)*10);
    
        fill ( 255, 255, 255); //wt
        rect(x3, y3, mySound.left.get(i)*20, mySound.left.get(i)*10);
    
        fill (255, 165, 0); //orange
        rect(x, y, mySound.right.get(i)*10, mySound.left.get(i)*10);
    
    
        fill(255); //wt
        rect(x3, y3, mySound.right.get(i)*10, mySound.right.get(i)*20);
    }
    
    n4 += 0.008;
    n6 += 0.04;
    
    }
}
