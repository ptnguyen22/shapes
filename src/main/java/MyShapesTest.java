// File name: pnA3

// Written by: Peter Nguyen
 
// Description: Test client to run all the classes.
//              
// Challenges: Figuring out how to set all objects as Shapes objects and then
//             how to access subclass methods from arraylist reference of data
//             type Shapes. How to get max objects back as subclass types or a 
//             way to use subclass methods for max objects.
// Time Spent: 2 hours

// Revision History:
// Date:        By:      Action:
// ---------------------------------------------------
/* 02/10/20   Peter Nguyen  Created class, arraylist, and default objects                     
 * 
 * 02/12/20   Peter Nguyen  Made for loop to run through each object in array
 *      
 * 02/18/20   Peter Nguyen  Made messages to display object information
 * 
 * 03/09/20   Peter Nguyen  Changed class to work with frame (gui) class
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

