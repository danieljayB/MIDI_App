import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import de.voidplus.leapmotion.*; 
import processing.serial.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class MIDI_App extends PApplet {






LeapMotion leap;

PFont unlatchFont;

 
////BOX CLASSES//////////
myBox new_box;
myBox newBox2;
knob newKnob;
//////////////////////////



////////GLOBAL LEAP MOTION FUNCTIONS///////////

  float progress;
  float box_x;
  float newBoxX = box_x-150;
 int     hand_id;
///////////////////////////////////////////////   


   
boolean centerswitch = false;   
 boolean unlatcher = false;  
 boolean reset = false; //unlatches middle button
 boolean neutral=false;
   
 
  
public void setup(){
  
  unlatchFont = loadFont("AdobeGothicStd-Bold-20.vlw");
textFont(unlatchFont, 20);
 // ID_Check.addValue(hand_id );
  //ID_Check.addValue(  hand_id );


  

   //exit();
  
  
  size(700, 700, P3D);
  
  
  //background(1);
  
   
  
  new_box= new myBox();
  newBox2= new myBox();
  newKnob = new knob();
  
  noStroke();
  
 // noStroke(); fill(255);
  // ...
    
  leap = new LeapMotion(this).withGestures();
  // leap = new LeapMotion(this).withGestures("circle, swipe, screen_tap, key_tap");
  // leap = new LeapMotion(this).withGestures("swipe"); // Leap detects only swipe gestures.

 
  
}

public void draw(){
  
 
 background(230);

    
    

///////////////////////////////////////////////////////////////////////
rectMode(CENTER);
 blendMode(DIFFERENCE);
 fill(20);
 rect(width/2, height/2, 500, 150);
 
 machineLED();          //STATUS LED
 new_box.neutralState(); //CENTER BUTTON
   newBox2.box2();  //LEFT BUTTON
newKnob.centerKnob();





//////////////////////////////////////////////////////////////////////
 
    int fps = leap.getFrameRate();
  
//////////////////////////////HANDS////////////////////////////////
  for(Hand hand : leap.getHands()){
    hand.draw();
            hand_id          = hand.getId();
    PVector hand_position    = hand.getPosition();
    PVector hand_stabilized  = hand.getStabilizedPosition();
    PVector hand_direction   = hand.getDirection();
    PVector hand_dynamics    = hand.getDynamics();
    float   hand_roll        = hand.getRoll();
    float   hand_pitch       = hand.getPitch();
    float   hand_yaw         = hand.getYaw();
    float   hand_time        = hand.getTimeVisible();
    PVector sphere_position  = hand.getSpherePosition();
    float   sphere_radius    = hand.getSphereRadius();
//////////////////////////////////////////////////////////////////////

//println(hand_id);




  if (
      hand_position.x >  width/2+150-110 &&      hand_position.x <  width/2+150+110 &&
        hand_position.y > height/2-110   &&      hand_position.y < height/2+110 && unlatcher==false
      
      ) {
        
        
        newKnob.slideProgress();
        
        
      }






////////////////////// FINGERS////////////////////////////////////////
    for(Finger finger : hand.getFingers()){
      // Basics
      finger.draw();
      int     finger_id         = finger.getId();
       PVector finger_position   = finger.getPosition();
      PVector finger_stabilized = finger.getStabilizedPosition();
      PVector finger_velocity   = finger.getVelocity();
      PVector finger_direction  = finger.getDirection();
      float   finger_time       = finger.getTimeVisible();
////////////////////////////////////////////////////////////////////////      
      // Touch Emulation
      int     touch_zone        = finger.getTouchZone();
      float   touch_distance    = finger.getTouchDistance();
      
      //println("finger X Position" + finger_position.x);
      //println("finger Y Position" + finger_position.y);
      
//////////////////////////////////////////////////////////////////////////       
        
 




      switch(touch_zone){
        case -1: // None
          break;
        case 0: // Hovering
          // println("Hovering (#"+finger_id+"): "+touch_distance);
          break;
        case 1: // Touching
          // println("Touching (#"+finger_id+")");
          break;
      }
      
      
     
        
    }
    
    // TOOLS
    for(Tool tool : hand.getTools()){
      // Basics
      tool.draw();
      int     tool_id           = tool.getId();
      PVector tool_position     = tool.getPosition();
      PVector tool_stabilized   = tool.getStabilizedPosition();
      PVector tool_velocity     = tool.getVelocity();
      PVector tool_direction    = tool.getDirection();
      float   tool_time         = tool.getTimeVisible();
      
      // Touch Emulation
      int     touch_zone        = tool.getTouchZone();
      float   touch_distance    = tool.getTouchDistance();
      
      switch(touch_zone){
        

        case -1: // None
          break;
        case 0: // Hovering
          // println("Hovering (#"+tool_id+"): "+touch_distance);
          break;
        case 1: // Touching
          // println("Touching (#"+tool_id+")");
          break;
      }
    }
    
  }
  
  /////////////conditionals///////////////////////////////////
  



}


///////////////////C O N D I T I O N S////////////////////////////////////











//////////////////////// SWIPE GESTURE//////////////////////////////////////////////
public void leapOnSwipeGesture(SwipeGesture g, int state){
  int       id               = g.getId();
  Finger    finger           = g.getFinger();
  PVector   position         = g.getPosition();
  PVector   position_start   = g.getStartPosition();
  PVector   direction        = g.getDirection();
  float     speed            = g.getSpeed();
  long      duration         = g.getDuration();
  float     duration_seconds = g.getDurationInSeconds();

  switch(state){
    case 1: // Start
      break;
    case 2: // Update
      break;
    case 3: // Stop
      println("SwipeGesture: "+id);
      break;
  }
}

// CIRCLE GESTURE
public void leapOnCircleGesture(CircleGesture g, int state){
  int       id               = g.getId();
  Finger    finger           = g.getFinger();
  PVector   position_center  = g.getCenter();
   float         radius           = g.getRadius();
       progress         = g.getProgress();
  long      duration         = g.getDuration();
  float     duration_seconds = g.getDurationInSeconds();







  switch(state){
    case 1: // Start
      break;
    case 2: // Update
      break;
    case 3: // Stop
      println("CircleGesture: "+id);
      break;
  }
}

// SCREEN TAP GESTURE
public void leapOnScreenTapGesture(ScreenTapGesture g){
  int       id               = g.getId();
  Finger    finger           = g.getFinger();
   PVector position_tap        = g.getPosition();
  PVector   direction        = g.getDirection();
  long      duration         = g.getDuration();
  float     duration_seconds = g.getDurationInSeconds();

 // println("ScreenTapGesture: "+id);
 println("ScreenTapGesture Position: "+position_tap );
  
   
  if (
      position_tap.x > width/2-110   &&      position_tap.x < width/2+110 &&
        position_tap.y > height/2-110   &&      position_tap.y < height/2+110
      
      ) {
        
        if(centerswitch==false){
          
        
        new_box.pressed();
        println("PRESSED");
     
         
         centerswitch = true;
    

 

       
        }
      }
      
     
    
      
/////////////////////////////  U N L A T C H E R ///////////////////////////////////////
        if (
      position_tap.x >  width/2-150-110 &&      position_tap.x <  width/2-150+110 &&
        position_tap.y > height/2-110   &&      position_tap.y < height/2+110 && unlatcher==false
      
      ) {
        
         
          println("UNLATCHED");
          unlatcher = true;
          

        
      
      
      }
   
///////////////////////////////////////////////////////////////////////////////////////////  

    
    
}

// KEY TAP GESTURE
public void leapOnKeyTapGesture(KeyTapGesture g){
  int       id               = g.getId();
  Finger    finger           = g.getFinger();
  PVector   position         = g.getPosition();
  PVector   direction        = g.getDirection();
  long      duration         = g.getDuration();
  float     duration_seconds = g.getDurationInSeconds();
  
  println("KeyTapGesture: "+id);
  // println("KeyTapGesture Position: "+position);
}




public void leapOnInit(){
  // println("Leap Motion Init");
}
public void leapOnConnect(){
  // println("Leap Motion Connect");
}
public void leapOnFrame(){
  // println("Leap Motion Frame");
}
public void leapOnDisconnect(){
  // println("Leap Motion Disconnect");
}
public void leapOnExit(){
  // println("Leap Motion Exit");
}
///////////////////////// B O X   C L A S S /////////////////////////
class myBox{
  

  int Press;
  float box_size;
  float box_y;
  
 public myBox(){
    

   
      box_size = 110;
   box_y = height/2;
 box_x = width/2;
  }
    
public void neutralState(){
  
  
    
 blendMode(DIFFERENCE);
  fill(80);
  rectMode(CENTER);
  strokeWeight(4);
   rect(box_x, box_y, box_size, box_size);
  ellipseMode(CENTER);
   blendMode(DIFFERENCE);   
fill(10);
rect(width/2, height/2, 80,80);


  
  
  
  if(centerswitch==true){
    
     blendMode(DIFFERENCE);
  fill(5);
  rectMode(CENTER);
  strokeWeight(4);
  ellipseMode(CENTER);
   blendMode(DIFFERENCE);   
fill(30);
rect(width/2, height/2, 60,60);

 
   
  rect(box_x, box_y, box_size, box_size);
    
    //
    
  }

    
  
  }
  
  
  public void pressed()
  {
   
 strokeWeight(4);
 blendMode(DIFFERENCE);
    fill(80);
    rectMode(CENTER);
 
 rect(box_x, box_y, box_size, box_size);

  }
  
  
  public void hover(){
    
   blendMode(DIFFERENCE);
    fill(80);
    rectMode(CENTER);
  strokeWeight(4);
 rect(box_x, box_y, box_size, box_size);
  
}

public void box2(){
blendMode(DIFFERENCE);
  fill(100);
  rectMode(CENTER);
  strokeWeight(4);
 
 rect(box_x-150, box_y, box_size, box_size); 
 textSize(20);
 fill(255);


 textAlign(CENTER, CENTER);
 text("UNLATCH", box_x-150, box_y-23);
  
   
     if(unlatcher==true){
   
       
       int animate = millis();   
  fill(animate % 255);
blendMode(DIFFERENCE);
  //fill(190);
  rectMode(CENTER);
  strokeWeight(4);
 rect(width/2-150, height/2, 110, 110); 
unlatcher=false;
centerswitch=false;

     } 
        
}       
      
         
}

/*
static class ID_Check {
  static final int NUMS = 2;
  static final int[] numbers = new int[NUMS];
  static int i;
int ID1;
  int ID2;

  static void addValue(int id) {
id = hand_id;
    if (i < NUMS)  numbers[i++] = id;

    if (i == NUMS) {
   final int ID1 = numbers[0],ID2 = numbers[1];
    
  
 
    
    
    println(ID1 + "..." + ID2 );
    
    }
  }
}

*/
/////////////////// C I R C L E   S L I D E R ///////////////////////////

// Spin Function



class knob{
  
  int colorKnob;
  int knobCenter;
  float newposition;
  float posCon;
  
  
  
  knob(){
    
       colorKnob = color(0xff438D80);
       knobCenter = color(0xff4C787E);
    
  }

  public void spin(float tx, float ty, float twd, float tht, float tang, float tang2 ){
blendMode(DIFFERENCE);
 fill(80);
    arc(tx, ty, twd, tht, radians(0), radians(tang) );
  

  }
  
      public void slideProgress(){
     //fill(colortest);
     smooth(4);
     
     //strokeWeight(4);
   posCon = constrain(progress, 0.0f, 15.0f);
newposition = map(posCon, 0, 15, 0, 360);
      spin(width/2+150, height/2, 110, 110, newposition, PIE);
 
      


 
      }
      
      public void centerKnob(){
        spin(width/2+150, height/2, 110, 110, newposition, PIE);
             ellipseMode(CENTER);
   blendMode(DIFFERENCE);   
fill(60);
ellipse(width/2+150, height/2, 60,60);
fill(255);
textSize(20);
text(PApplet.parseInt(newposition),width/2+150, height/2);

      }
      
     

}


////////////////////////////////////////////////////////////////////////////
public void machineLED(){
  
 
  int m = millis();   
ellipseMode(CORNER);  // Set ellipseMode is CORNER
  fill(m % 255);
ellipse(width/2+230, height/2-70, 10, 10);

}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "MIDI_App" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
