// File name: pnA3

// Written by: Peter Nguyen
 
// Description: Superclass for all shapes             
//              
// Challenges: Figuring out what all shapes have in common to use in this 
//             superclass. Learning about Color in api and how to use it.
// Time Spent: 2 hour

// Revision History:
// Date:        By:      Action:
// ---------------------------------------------------
/* 02/01/20   Peter Nguyen  Created class, instance variables, and constructors                     
 * 
 * 02/03/20   Peter Nguyen  Created set and get methods each coordinate value,
 *                          method to set color and method to format coordinate,
 *                          and overriding toString()
 * 04/12/20   Peter Nguyen  Added new constructors and instance variables
*/                          

import java.awt.Color;

abstract class Shapes implements GeometricObject{
    
    //Instance variables for coordinates
    public int x1,x2,y1,y2;
    //Objects to hold 2 points
    Points point1,point2;
    //Object to hold color
    Color color;
    boolean filled;
    float line;
    
    //Empty consructor that sets all values to 0 and color to black
    public Shapes(){
        x1=0;
        x2=0;
        y1=0;
        y2=0;
        point1=new Points(0,0);
        point2=new Points(0,0);
        color=Color.BLACK;
    }
    
    //Constructor that passes 4 integers to make 2 points and default color to black
    public Shapes(int x1,int y1,int x2,int y2){
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        point1=new Points(x1,y1);
        point2=new Points(x2,y2);
        color=Color.BLACK;
    }
    
    //Constructor that passes 4 integers to make 2 points and color object 
    public Shapes(int x1,int y1,int x2,int y2,Color color){
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        point1=new Points(x1,y1);
        point2=new Points(x2,y2);
        this.color=color;
    }
    
    //New constructor that holds line width for g2d
    public Shapes(int x1,int y1,int x2,int y2,Color color,boolean filled){
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        this.filled=filled;
        point1=new Points(x1,y1);
        point2=new Points(x2,y2);
        this.color=color;
    }
    
    //new constructor that adds boolean and float for line width
    public Shapes(int x1,int y1,int x2,int y2,Color color,boolean filled,float line){
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        this.filled=filled;
        point1=new Points(x1,y1);
        point2=new Points(x2,y2);
        this.color=color;
        this.line=line;
    }
    
    
    //Set color method
    public void setColor(Color color){
        this.color=color;
    }
    
    //Get color
    public Color getColor(){
        return color;
    }
    
    //Get first point
    public Points getPoint1(){
        return point1;
    }
    
    //Get second point
    public Points getPoint2(){
        return point2;
    }
    
    //Format coordinates of the 2 points
    public String getCoordinatesAsString(){
        return String.format("%s, %s", point1, point2);
    }
    
    //Override toString() to show what coordinates are
    @Override
    public String toString(){
        return String.format("Coordinates of %s are: \n%s",getName(), getCoordinatesAsString());
    }
}
