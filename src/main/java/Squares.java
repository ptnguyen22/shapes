// File name: pnA3

// Written by: Peter Nguyen
 
// Description: Subclass of TwoDimensionalShapes to make squares            
//              
// Challenges: Figuring out constructors interact with super constructors
//             
// Time Spent: 1 hour

// Revision History:
// Date:        By:      Action:
// ---------------------------------------------------
/* 02/06/20   Peter Nguyen  Created class and constructors                      
 * 
 * 02/08/20   Peter Nguyen  Created set and get methods for area,                   
 *                          perimeter and name.  
 * 04/13/20   Peter Nguyen  Added new constructors, paint and toString methods
*/    

import java.awt.*;
import java.awt.geom.*;

public class Squares extends TwoDimensionalShapes {
    
    //Empty constructor
    public Squares(){
        super();
    }
    
    //Constructor that passes 4 integers to make 2 points
    public Squares(int x1, int y1, int x2, int y2){
         super(x1,y1,x2,y2);
    }
    
    //Constructor that passes 4 integers to make 2 points and color object
    public Squares(int x1, int y1, int x2, int y2, Color color, 
            boolean filled){
        super(x1,y1,x2,y2,color,filled);
    }
    
    //new constructor with added boolean and float arguements
    public Squares(int x1, int y1, int x2, int y2, Color color, 
            boolean filled,float line){
        super(x1,y1,x2,y2,color,filled,line);
    }
    
    //Get area of square
    @Override
    public double getArea(){
        return getWidth()*getHeight();
    }
    
    //Get perimeter of square
    @Override
    public double getPerimeter(){
        return (getWidth()*2.0)+(getHeight()*2.0);
    }
    
    //Override toString() to return "Square"
    @Override
    public String getName(){
        return "Square";
    }
    
    //Paint method to draw object using graphics2d
    public void paint(Graphics2D g2d){
        g2d.setPaint(color);
        g2d.setStroke(new BasicStroke(line));
        if (filled==true)
            g2d.fill(new Rectangle2D.Double(getUpperLeftX(),getUpperLeftY(),getWidth(),getWidth()));
        else
            g2d.draw(new Rectangle2D.Double(getUpperLeftX(),getUpperLeftY(),getWidth(),getWidth()));
    }
    
    //Comprehensive toString to display text area results
    @Override
    public String toString(){
        return String.format("[%s] with %s and %s\nArea: %.2f\nPerimeter: %.2f",
                    getName(), getPoint1(), getPoint2(),getArea(),getPerimeter());
    }
}
