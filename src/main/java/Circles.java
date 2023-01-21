// File name: pnA3

// Written by: Peter Nguyen
 
// Description: Subclass of TwoDimensionalShapes to make circles
//                       
// Challenges: Figuring out how the circle is formed with 2 points
//             
// Time Spent: 1 hour

// Revision History:
// Date:        By:      Action:
// ---------------------------------------------------
/* 02/03/20   Peter Nguyen  Created class and constructors                      
 * 
 * 02/05/20   Peter Nguyen  Created set and get methods for area, radius,                   
 *                          perimeter and name. 
 * 04/12/20   Peter Nguyen  Added new constructor, added paint method and
 *                          comprehensive toString() method.
*/   
import java.awt.*;
import java.awt.geom.*;

public class Circles extends TwoDimensionalShapes{
    private final double radius;
    
    //Empty constructor and setting instance variable radius=0
    public Circles(){
        super();
        radius=0.0;
    }
    
    //Constructor with 4 integers to make 2 points and setting radius to width/2
    public Circles(int x1, int y1, int x2, int y2){
        super(x1,y1,x2,y2);
        radius=getWidth()/2.0;
    }
    
    //New constructor with filled option
    public Circles(int x1, int y1, int x2, int y2,Color color, boolean filled){
        super(x1,y1,x2,y2,color,filled);
        radius=getWidth()/2.0;
    }
    
    //new constructor that for filled option and line width
    public Circles(int x1, int y1, int x2, int y2,Color color, boolean filled,
            float line){
        super(x1,y1,x2,y2,color,filled,line);
        radius=getWidth()/2.0;
    }
    
    //Get objects radius
    public double getRadius(){
        return radius;
    }
    
    //Implement getArea() method from TwoDimensionalShapes class
    @Override
    public double getArea(){
        return radius*radius*Math.PI;
    }
    
    //Implement getPerimeter() method from TwoDimensionalShapes class
    @Override
    public double getPerimeter(){
        return getWidth()*Math.PI;
    }
    
    //Implement getName() method from TwoDimensionalShapes class
    @Override
    public String getName(){
        return "Circle";
    }
    
    //paint method to draw shape object using graphics2d ellipse shape
    public void paint(Graphics2D g2d){
        g2d.setPaint(color);
        g2d.setStroke(new BasicStroke(line));
        if (filled==true)
            g2d.fill(new Ellipse2D.Double(getUpperLeftX(),getUpperLeftY(),getWidth(),getWidth()));
        else
            g2d.draw(new Ellipse2D.Double(getUpperLeftX(),getUpperLeftY(),getWidth(),getWidth()));
    }
    
    //toString that will display results for text area
    @Override
    public String toString(){
        return String.format("[%s] with %s and %s\nArea: %.2f\nPerimeter: %.2f",
                    getName(), getPoint1(), getPoint2(),getArea(),getPerimeter());
    }
}
