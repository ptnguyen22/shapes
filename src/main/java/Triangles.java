// File name: pnA3

// Written by: Peter Nguyen
 
// Description: Subclass of TwoDimensionalShapes to make lines            
//              
// Challenges: Figuring out how to calculate the 3 points needed to draw a
//             triangle from the given 2 points. How to create a path to draw
//             a polygon.
// Time Spent: 1 hour

// Revision History:
// Date:        By:      Action:
// ---------------------------------------------------
/* 04/15/20 Peter Nguyen Created class and methods.
 * 04/16/20 Peter Nguyen Fixed up point calculations and finished path code
 *                       to draw polygon.
*/  

import java.awt.*;
import java.awt.geom.*;

public class Triangles extends TwoDimensionalShapes {
    
    Points point1,point2,point3;
    
    public Triangles(){
        super();
    }
    public Triangles(int x1, int y1, int x2, int y2, Color color, boolean filled,
            float line){
        super(x1,y1,x2,y2,color,filled,line);
        point1=new Points(getUpperLeftX()+(getWidth()/2),getUpperLeftY());
        point2=new Points(getUpperLeftX(),
                getLowPoint().getY());
        point3=getLowPoint();
    }
    
    @Override
    public Points getPoint1(){
        point1=new Points(getUpperLeftX()+(getWidth()/2),getUpperLeftY());
        return point1;
    }
    
    @Override
    public Points getPoint2(){
        return new Points(getLowPoint().getX()-getUpperLeftX(),
                getLowPoint().getY());
    }
    public Points getPoint3(){
        return getLowPoint();
    }
    
    @Override
    public double getArea(){
        return .5*getHeight()*getWidth();
    }
    
    @Override
    public double getPerimeter(){
        return Math.sqrt(Math.pow(getPoint1().getX()-getPoint2().getX(), 2)
                +Math.pow(getPoint1().getY()-getPoint2().getY(), 2));
    }
    
    @Override 
    public String getName(){
        return "Triangle";
    }
    
    public void paint(Graphics2D g2d){
        Path2D p=new Path2D.Double();
        p.moveTo(point1.getX(), point1.getY());
        p.lineTo(point2.getX(),point2.getY());
        p.lineTo(point3.getX(), point3.getY());
        p.lineTo(point1.getX(), point1.getY());
        p.closePath();
        g2d.setPaint(color);
        g2d.setStroke(new BasicStroke(line));
        if (filled==true)
            g2d.fill(p);
        else
            g2d.draw(p);
    }
    
    @Override
    public String toString(){
        return String.format("[%s] with %s and %s\nArea: %.2f\nPerimeter: %.2f",
                    getName(), getPoint1(), getPoint2(),getArea(),getPerimeter());
    }
}
