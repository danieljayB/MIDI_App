void machineLED(){
  
 
  int m = millis();   
ellipseMode(CORNER);  // Set ellipseMode is CORNER
  fill(m % 255);
ellipse(width/2+230, height/2-70, 10, 10);

}

