/////////////////// C I R C L E   S L I D E R ///////////////////////////

// Spin Function



class knob{
  
  color colorKnob;
  color knobCenter;
  float newposition;
  float posCon;
  
  
  
  knob(){
    
       colorKnob = color(#438D80);
       knobCenter = color(#4C787E);
    
  }

  void spin(float tx, float ty, float twd, float tht, float tang, float tang2 ){
blendMode(DIFFERENCE);
 fill(80);
    arc(tx, ty, twd, tht, radians(0), radians(tang) );
  

  }
  
      void slideProgress(){
     //fill(colortest);
     smooth(4);
     
     //strokeWeight(4);
   posCon = constrain(progress, 0.0, 15.0);
newposition = map(posCon, 0, 15, 0, 360);
      spin(width/2+150, height/2, 110, 110, newposition, PIE);
 
      


 
      }
      
      void centerKnob(){
        spin(width/2+150, height/2, 110, 110, newposition, PIE);
             ellipseMode(CENTER);
   blendMode(DIFFERENCE);   
fill(60);
ellipse(width/2+150, height/2, 60,60);
fill(255);
textSize(20);
text(int(newposition),width/2+150, height/2);

      }
      
     

}


////////////////////////////////////////////////////////////////////////////
