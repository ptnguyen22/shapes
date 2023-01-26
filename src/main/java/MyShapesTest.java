//Peter Nguyen
 
// Description: Test client to run all the classes.
//              
/* Challenges: Figuring out how to set all objects as Shapes objects and then
               how to access subclass methods from arraylist reference of data
               type Shapes. How to get max objects back as subclass types or a 
               way to use subclass methods for max objects.
*/

import javax.swing.*;

public class MyShapesTest {
    public static void main( String args[] ){
        shapesGUI window=new shapesGUI();
        window.pack();
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setLocationRelativeTo(null);
        window.setVisible( true );
    }    
}

