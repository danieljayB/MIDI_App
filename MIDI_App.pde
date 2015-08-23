import de.voidplus.leapmotion.*;
import processing.serial.*;



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
   
 
  
void setup(){
  
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

void draw(){
  
 
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
void leapOnSwipeGesture(SwipeGesture g, int state){
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
void leapOnCircleGesture(CircleGesture g, int state){
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
void leapOnScreenTapGesture(ScreenTapGesture g){
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
void leapOnKeyTapGesture(KeyTapGesture g){
  int       id               = g.getId();
  Finger    finger           = g.getFinger();
  PVector   position         = g.getPosition();
  PVector   direction        = g.getDirection();
  long      duration         = g.getDuration();
  float     duration_seconds = g.getDurationInSeconds();
  
  println("KeyTapGesture: "+id);
  // println("KeyTapGesture Position: "+position);
}




void leapOnInit(){
  // println("Leap Motion Init");
}
void leapOnConnect(){
  // println("Leap Motion Connect");
}
void leapOnFrame(){
  // println("Leap Motion Frame");
}
void leapOnDisconnect(){
  // println("Leap Motion Disconnect");
}
void leapOnExit(){
  // println("Leap Motion Exit");
}
