// Peter Nguyen
 
// Description: Subclass of TwoDimensionalShapes to make rectangles            

import java.awt.*;
import java.awt.geom.*;
public class Rectangles extends TwoDimensionalShapes{
    //Empty constructor
    public Rectangles(){
        super();
    }
    
    //Constructor that passes in 4 integers
    public Rectangles(int x1, int y1, int x2, int y2){
        super(x1,y1,x2,y2);
    }
    
    //Constructor that passes 4 integers and color object
    public Rectangles(int x1, int y1, int x2, int y2, Color color, 
            boolean filled){
        super(x1, y1, x2, y2, color, filled); 
    }
    
    //New constructor that holds line width for g2d
    public Rectangles(int x1, int y1, int x2, int y2, Color color, 
            boolean filled,float line){
        super(x1, y1, x2, y2, color, filled,line); 
    }
    
    //Get the area a=w*l
    @Override
    public double getArea(){
        return getWidth()*getHeight();
    }
    
    //Get the perimeter p=(2*w)+(2*l)
    @Override
    public double getPerimeter(){
        return (getWidth()*2.0)+(getHeight()*2.0);
    }
    
    //Override toString() to return "Rectangle" literal
    @Override 
    public String getName(){
        return "Rectangle";
    }
    
    //paint method using graphics2d to draw a rectangle object
    public void paint(Graphics2D g2d){
        g2d.setPaint(color);
        g2d.setStroke(new BasicStroke(line));
        if (filled==true)
            g2d.fill(new Rectangle2D.Double(getUpperLeftX(),getUpperLeftY(),getWidth(),getHeight()));
        else
            g2d.draw(new Rectangle2D.Double(getUpperLeftX(),getUpperLeftY(),getWidth(),getHeight()));
    }
    
    //comprehensive toString to display text area results
    @Override
    public String toString(){
        return String.format("[%s] with %s and %s\nArea: %.2f\nPerimeter: %.2f",
                    getName(), getPoint1(), getPoint2(),getArea(),getPerimeter());
    }
}
