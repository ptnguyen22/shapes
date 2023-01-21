// File name: pnA3

// Written by: Peter Nguyen
 
// Description: Subclass of TwoDimensionalShapes to make ovals          
//              
// Challenges: Figuring out perimeter and area calculations. Calculations
//             are an estimate because cannot get exact answer with just 2 points.
// Time Spent: 1 hour

// Revision History:
// Date:        By:      Action:
// ---------------------------------------------------
/* 04/15/20   Peter Nguyen  Created class and constructors and methods  
 * 04/16/20   Peter Nguyen  Finished class 
*/  
import java.awt.*;
import java.awt.geom.*;
import java.lang.*;

public class Ovals extends TwoDimensionalShapes {
    
    //Empty constructor
    public Ovals(){
        super();
    }
    
    //Constructor that passes 4 integers to make 2 points
    public Ovals(int x1, int y1, int x2, int y2){
         super(x1,y1,x2,y2);
    }
    
    //Constructor that passes 4 integers to make 2 points,color,and boolean
    public Ovals(int x1, int y1, int x2, int y2, Color color, 
            boolean filled){
        super(x1,y1,x2,y2,color,filled);
    }
    
    //Contructor with all values needed to create and draw the object
    public Ovals(int x1, int y1, int x2, int y2, Color color, 
            boolean filled,float line){
        super(x1,y1,x2,y2,color,filled,line);
    }
    
    //override getArea from TwoDimensionalShapes that returns area of object
    public double getArea(){
        return (.5*getWidth())*(.5*getHeight())*Math.PI;
    }
    
    //Implement getPerimeter() method from TwoDimensionalShapes class
    @Override
    public double getPerimeter(){
        return 2*Math.PI*Math.sqrt((Math.pow(getWidth(),2)+Math.pow(getHeight(),2)/2)) ;
    }
    
    //return name of shape
    @Override
    public String getName(){
        return "Oval";
    }
    
    //paint method to draw shape object
    public void paint(Graphics2D g2d){
        g2d.setPaint(color);
        g2d.setStroke(new BasicStroke(line));
        if (filled==true)
            g2d.fill(new Ellipse2D.Double(getUpperLeftX(),getUpperLeftY(),getWidth(),getHeight()));
        else
            g2d.draw(new Ellipse2D.Double(getUpperLeftX(),getUpperLeftY(),getWidth(),getHeight()));
    }
    
    //Comprehensive toString over ride
    @Override
    public String toString(){
        return String.format("[%s] with %s and %s\nArea: %.2f\nPerimeter: %.2f",
                    getName(), getPoint1(), getPoint2(),getArea(),getPerimeter());
    }
}
