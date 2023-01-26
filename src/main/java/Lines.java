//Peter Nguyen
 
//Subclass of TwoDimensionalShapes to make lines        
  
import java.awt.*;
import java.awt.geom.*;

public class Lines extends Shapes{
    
    //empty constructor
    public Lines(){
        super();
    }
    
    //Constructor that passes 4 integers to make 2 points
    public Lines(int x1, int y1, int x2, int y2){
        super(x1, y1, x2, y2);
    }
    
    //Constructor that passes 4 integers to make 2 points and color paremeter
    public Lines(int x1, int y1, int x2, int y2, Color color){
        super(x1, y1, x2, y2, color);
    }
    
    //new constructor with boolean filled to draw and line width arguement
    public Lines(int x1, int y1, int x2, int y2, Color color,boolean filled, 
            float line){
        super(x1, y1, x2, y2, color,filled,line);
    }
    
    //Implement getName() method from superclass
    @Override
    public String getName(){
        return "Line";
    }
    
    //Paint method that will draw object
    public void paint(Graphics2D g2d){
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(line));
        g2d.draw(new Line2D.Double(x1,y1,x2,y2));
    }
    
    //Comprehensive toString for text area results
    @Override
    public String toString(){
        return String.format("[%s] with %s and %s",
                    getName(), getPoint1(), getPoint2());
    }
}
