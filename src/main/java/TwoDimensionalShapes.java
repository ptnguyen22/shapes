// File name: pnA3

// Written by: Peter Nguyen
 
// Description: Subclass of shapes and superclass to specific 2d shapes
//              
//              
// Challenges: Implementing Comparable<> from API and using it. Figuring out
//             how this class will interact with Shapes class and subclasses.
// Time Spent: 2 hours

// Revision History:
// Date:        By:      Action:
// ---------------------------------------------------
/* 02/01/20   Peter Nguyen  Created class and constructors                      
 * 
 * 02/02/20   Peter Nguyen  Made getter methods and abstract methods
 *      
 * 02/03/20   Peter Nguyen  Made compareTo() and max()
 *
 * 04/16/20   Peter Nguyen  Added new instance Points variable and method to
 *                          return the lower point of the 2
*/    

import java.awt.Color;
abstract class TwoDimensionalShapes extends Shapes 
        implements Comparable<TwoDimensionalShapes>{
    
    //Variable to find bottom point for triangle
    Points lowPoint;
    
    //Empty constructor with filled as default false
    public TwoDimensionalShapes(){
        super();
        filled=false;
    }
    
    //Constructor that passes 4 integers to make 2 points
    public TwoDimensionalShapes(int x1, int y1, int x2, int y2){
        super(x1,y1,x2,y2);
    }
    
    //Constructor that passes 4 integers to make 2 points and color object
    public TwoDimensionalShapes(int x1, int y1, int x2, int y2, Color color, 
            boolean filled){
        super(x1,y1,x2,y2,color,filled);
    }
    
    //New constructor that holds line width for g2d
    public TwoDimensionalShapes(int x1, int y1, int x2, int y2, Color color, 
            boolean filled,float line){
        super(x1,y1,x2,y2,color,filled,line);
        }
    
    //Return smaller x value of 2 points
    public int getUpperLeftX(){
        if (point1.getX()<point2.getX())
            return point1.getX();
        else
            return point2.getX();
    }
    
    //Return smaller y value of 2 points
    public int getUpperLeftY(){
        if (point1.getY()<point2.getY())
            return point1.getY();
        else
            return point2.getY();
    }
    
    //Returns the lower point of the 2
    public Points getLowPoint(){
        if (point1.getY()<point2.getY())
            return point2;
        else
            return point1;
    }
    
    //Return width 
    public int getWidth(){
        return Math.abs(point1.getX()-point2.getX());
    }
    
    //Return height 
    public int getHeight(){
        return Math.abs(point1.getY()-point2.getY());
    }
    
    //Check value of filled
    public boolean isFilled(){
        return filled;
    }
    
    //Change filled value
    public void setFilled(boolean filled){
        this.filled=filled;
    }
    
    //Abstract  method to get area
    public abstract double getArea();
    
    //Abstract method to get perimeter
    public abstract double getPerimeter();
    
    //Override Comparable class method 
    @Override
    public int compareTo(TwoDimensionalShapes o){
        //If first object is larger return 1
        if (this.getArea()>o.getArea())
            return 1;
        //Return 0 if both are same area
        else if (this.getArea()==o.getArea())
            return 0;
        //return -1 if second object is larger
        else 
            return -1;
    }
    
    //Max method to return larger object as a TwoDimensionalShape object
    public static TwoDimensionalShapes max(TwoDimensionalShapes o1,
            TwoDimensionalShapes o2) {
        if (o1.compareTo(o2) > 0)
            return o1;
        else
            return o2;
    }   
}
