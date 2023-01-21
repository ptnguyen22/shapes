// File name: pnA3

// Written by: Peter Nguyen
 
// Description: Subclass of TwoDimensionalShapes to make lines        
//              
// Challenges: Figuring out how constructors will interact with superclass
//             constructors
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
