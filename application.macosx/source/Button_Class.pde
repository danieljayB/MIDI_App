///////////////////////// B O X   C L A S S /////////////////////////
class myBox{
  

  color Press;
  float box_size;
  float box_y;
  
 public myBox(){
    

   
      box_size = 110;
   box_y = height/2;
 box_x = width/2;
  }
    
void neutralState(){
  
  
    
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
  
  
  void pressed()
  {
   
 strokeWeight(4);
 blendMode(DIFFERENCE);
    fill(80);
    rectMode(CENTER);
 
 rect(box_x, box_y, box_size, box_size);

  }
  
  
  void hover(){
    
   blendMode(DIFFERENCE);
    fill(80);
    rectMode(CENTER);
  strokeWeight(4);
 rect(box_x, box_y, box_size, box_size);
  
}

void box2(){
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

