// File name: pnA3

// Written by: Peter Nguyen
 
// Description: Class to make points for all shapes
//              
//              
// Challenges: None
// Time Spent: 30 minutes

// Revision History:
// Date:        By:      Action:
// ---------------------------------------------------
/* 02/03/20   Peter Nguyen  Created class,construcor,getter methods,                  
 *                          override toString()
*/  
public class Points {
    
    //Instance variables to make a point
    private int x,y;
    
    //Empty constructor sets point to (0,0)
    public Points(){
        x=0;
        y=0;
    }
    
    //Constructor with 2 integer parameters to make a point
    public Points(int x,int y){
        this.x=x;
        this.y=y;
    }
    
    //Getter method to return x value of point
    public int getX(){
        return x;
    }
    
    //Getter method to return y value of point
    public int getY(){
        return y;
    }
    
    //Override toString() to standard format of a point
    @Override
    public String toString(){
        return String.format("(%d,%d)",x,y);
    }
}
