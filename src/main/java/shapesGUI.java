// File name: pnA3
// Written by: Peter Nguyen
// Description: Graphics class using jframe with inner classes that create jpanel 
//              components and act as action listeners.
// Challenges: paintComponent() method would cause problems with other jcomponents
//             unless I called the super so I had to figure out a way to
//             keep shapes drawn that don't get erased from the super call.
//             Took a while to plan how all the inner classes interact and ended 
//             up reworking the shapes classes to draw the objects and stored all 
//             the created shape objects in array lists that get redrawn when
//             repaint() is called. Also creating code to change points so user
//             can click and release using any corners and it will draw within
//             those two corners.
// Time Spent: 8 hours

// Revision History:
// Date:        By:      Action:
// --------------------------------------------------- 
/* 04/10/20   Peter Nguyen  Created class, started on gui components.
 * 04/12/20   Peter Nguyen  Started paint method and plan to store drawn objects.
 * 04/13/20   Peter Nguyen  Reworked paint method and created a system of
 *                          storage for all drawn objects.
 * 04/14/20   Peter Nguyen  Finished menu and menu items, started on implmentation
 *                          of text items and font system.
 * 04/15/20   Peter Nguyen  Finished functional code and optimized some logic.
 * 04/16/20   Peter Nguyen  Cleaned up code and comments. Created borders and
 *                          added format and styling to gui.
*/ 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.*;
import java.awt.geom.*;
import java.lang.Math;
import java.util.*;

public class shapesGUI extends JFrame{
    
    //String list of shapeNames
    final private String shapeNames[]={"Rectangle","Circle","Square","Line",
        "Triangle","Oval"};
    //JComponent declarations
    private JCheckBox fillBox;
    private JComboBox shapesCombo;
    private JLabel lineWidth,shapesLabel,cursor;
    private JPanel options1, options2,options3, resultsPanel;
    private JButton colorButton,undoButton,clearButton;
    private JTextField lineText;
    private drawPanel test;
    private optionsPanel opt;
    private JTextArea textArea;
    //Declarations for shape objects
    private Color col=Color.BLUE,fontColor=Color.BLACK,
            fontBackground=Color.LIGHT_GRAY;
    private float lineStroke;
    private Points clickPoint, releasePoint, cursorLocation;
    private int x1,x2,y1,y2;
    //Declarations for menus and components
    private final JMenu fileMenu,textMenu, shapeMenu, editMenu, colorMenu, fontMenu,
            backgroundMenu;
    private final JMenuItem circleItem, squareItem, lineItem, triangleItem,
            ovalItem, rectangleItem, undoItem, clearItem, exitItem;
    private final JRadioButtonMenuItem sansItem,serifItem,monoItem,blackItem,
            blueItem,greenItem,redItem,whiteItem,cyanItem,yellowItem,lgrayItem;
    private JMenuBar b;
    private final JCheckBoxMenuItem italicItem,boldItem;
    private Font f;
    
    //Array lists to hold all drawn shape objects
    ArrayList<String> holder=new ArrayList<>();
    ArrayList<Circles> circArray=new ArrayList<>();
    ArrayList<Rectangles> rectArray=new ArrayList<>();
    ArrayList<Lines> lineArray=new ArrayList<>();
    ArrayList<Triangles> triArray=new ArrayList<>();
    ArrayList<Squares> sqArray=new ArrayList<>();
    ArrayList<Ovals> ovArray=new ArrayList<>();
    
    public shapesGUI(){
        //Set up main panels that will hold components
        opt=new optionsPanel();
        //Border for options panel
        opt.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED),"Options:",
                TitledBorder.CENTER,TitledBorder.DEFAULT_JUSTIFICATION));
        add(opt,BorderLayout.NORTH);
        test=new drawPanel();
        //Create border for drawing panel
        test.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                Color.GREEN,Color.MAGENTA));
        add(test,BorderLayout.CENTER);
        
        //Create actionlistener object from inner class
        listen lis=new listen();
        
        //Create menu objects
        fileMenu=new JMenu("File");
        fileMenu.setMnemonic('f');
        textMenu=new JMenu("Text");
        textMenu.setMnemonic('e');
        shapeMenu=new JMenu("Shape");
        shapeMenu.setMnemonic('s');
        editMenu=new JMenu("Edit");
        editMenu.setMnemonic('d');
        b=new JMenuBar();
        
        //Edit menu items
        undoItem=new JMenuItem("Undo");
        undoItem.setMnemonic('u');
        undoItem.addActionListener(lis);
        clearItem=new JMenuItem("Clear");
        clearItem.setMnemonic('a');
        clearItem.addActionListener(lis);
        editMenu.add(undoItem);
        editMenu.add(clearItem);
        
        //Add rectangle menu item, setmnemonic and action listener.
        rectangleItem=new JMenuItem("Rectangle");
        rectangleItem.setMnemonic('r');
        rectangleItem.addActionListener(lis);
        shapeMenu.add(rectangleItem);
        
        //Add circle menu item and set mnemonic and action listener
        circleItem=new JMenuItem("Circle");
        circleItem.setMnemonic('c');
        circleItem.addActionListener(lis);
        shapeMenu.add(circleItem);
        
        //Add square menu item and set mnemonic and add action listener
        squareItem=new JMenuItem("Square");
        squareItem.setMnemonic('s');
        squareItem.addActionListener(lis);
        shapeMenu.add(squareItem);
        
        //Add line menu item and set mnemonic and add action listener
        lineItem=new JMenuItem("Line");
        lineItem.setMnemonic('l');
        lineItem.addActionListener(lis);
        shapeMenu.add(lineItem);
        
        //Add triangle menu item and set mnemonic and add action listener
        triangleItem=new JMenuItem("Triangle");
        triangleItem.setMnemonic('t');
        triangleItem.addActionListener(lis);
        shapeMenu.add(triangleItem);
        
        //Add oval menu item, set mnemonic and add action listener
        ovalItem=new JMenuItem("Oval");
        ovalItem.setMnemonic('o');
        ovalItem.addActionListener(lis);
        shapeMenu.add(ovalItem);
        
        //Create exit menu item
        exitItem=new JMenuItem("Exit");
        exitItem.setMnemonic('i');
        exitItem.addActionListener(lis);
        
        //add items to file menu
        fileMenu.add(shapeMenu);
        fileMenu.add(editMenu);
        fileMenu.add(exitItem);
        
        //Create color menu, add color items,group and add action listener
        ButtonGroup cg=new ButtonGroup();
        colorMenu=new JMenu("Color");
        blackItem=new JRadioButtonMenuItem("Black");
        blackItem.addActionListener(lis);
        blackItem.setSelected(true);
        cg.add(blackItem);
        redItem=new JRadioButtonMenuItem("Red");
        redItem.addActionListener(lis);
        cg.add(redItem);
        blueItem=new JRadioButtonMenuItem("Blue");
        blueItem.addActionListener(lis);
        cg.add(blueItem);
        greenItem=new JRadioButtonMenuItem("Green");
        greenItem.addActionListener(lis);
        cg.add(greenItem);
        colorMenu.add(blackItem);
        colorMenu.add(redItem);
        colorMenu.add(greenItem);
        colorMenu.add(blueItem);
        
        //Create font menu, add items,group them, and add action listeners
        ButtonGroup g=new ButtonGroup();
        fontMenu=new JMenu("Font");
        sansItem=new JRadioButtonMenuItem("Sanserif");
        g.add(sansItem);
        sansItem.addActionListener(lis);
        monoItem=new JRadioButtonMenuItem("Monospaced");
        monoItem.addActionListener(lis);
        g.add(monoItem);
        serifItem=new JRadioButtonMenuItem("Serif");
        serifItem.addActionListener(lis);
        serifItem.setSelected(true);
        g.add(serifItem);
        //Add to font menu
        fontMenu.add(serifItem);
        fontMenu.add(sansItem);
        fontMenu.add(monoItem);
        fontMenu.addSeparator();
        //Add bold or italic items
        italicItem=new JCheckBoxMenuItem("Italic");
        italicItem.addActionListener(lis);
        boldItem=new JCheckBoxMenuItem("Bold");
        boldItem.addActionListener(lis);
        fontMenu.add(italicItem);
        fontMenu.add(boldItem);
        
        //Create background menu,items,group, and add action listeners
        backgroundMenu=new JMenu("Background");
        ButtonGroup g2=new ButtonGroup();
        whiteItem=new JRadioButtonMenuItem("White");
        whiteItem.addActionListener(lis);
        g2.add(whiteItem);
        cyanItem=new JRadioButtonMenuItem("Cyan");
        cyanItem.addActionListener(lis);
        g2.add(cyanItem);
        yellowItem=new JRadioButtonMenuItem("Yellow");
        yellowItem.addActionListener(lis);
        g2.add(yellowItem);
        lgrayItem=new JRadioButtonMenuItem("Light Gray");
        lgrayItem.addActionListener(lis);
        g2.add(lgrayItem);
        lgrayItem.setSelected(true);
        backgroundMenu.add(whiteItem);
        backgroundMenu.add(cyanItem);
        backgroundMenu.add(yellowItem);
        backgroundMenu.add(lgrayItem);
        
        //Add all to text menu
        textMenu.add(colorMenu);
        textMenu.addSeparator();
        textMenu.add(fontMenu);
        textMenu.addSeparator();
        textMenu.add(backgroundMenu);
        
        //Create menu bar and add menus
        b=new JMenuBar();
        b.add(fileMenu);
        b.add(textMenu);
        setJMenuBar(b);
        
        //Initialize default font
        f=new Font("Serif",Font.PLAIN,16);
        
        //initialize results panel for text area and jlabel
        resultsPanel=new JPanel();
        resultsPanel.setLayout(new BorderLayout());
        //Text area to display stats of shapes drawn
        textArea=new JTextArea("Draw a shape!",3,10);
        textArea.setFont(f);
        textArea.setEditable(false);
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLoweredBevelBorder(),"Shape Result:"));
        resultsPanel.add(textArea,BorderLayout.NORTH);
        
        //Jlabel to show coordinates
        cursor=new JLabel();
        cursorLocation=new Points(0,0);
        cursor.setText("Move mouse inside drawing panel to get coordinates!");
        resultsPanel.add(cursor,BorderLayout.SOUTH);
        
        add(resultsPanel,BorderLayout.SOUTH);
    }
    
    //Inner class to create separate panel for options
    public class optionsPanel extends JPanel{
        //Declare and set layout for all panels used in options GUI
        public optionsPanel(){
            setLayout(new GridLayout(3,1));
            //Declare jpanels used
            options1=new JPanel();
            options1.setLayout(new FlowLayout(FlowLayout.CENTER));
            options2=new JPanel();
            options2.setLayout(new FlowLayout(FlowLayout.CENTER));
            options3=new JPanel();
            options3.setLayout(new FlowLayout(FlowLayout.CENTER));

            //First Panel/line in options GUI
            shapesLabel=new JLabel("Shape:");
            options1.add(shapesLabel);
            shapesCombo=new JComboBox(shapeNames);
            options1.add(shapesCombo);
            colorButton=new JButton("Color...");
            colorButton.setBackground(col);
            colorButton.setForeground(Color.WHITE);
            colorButton.addActionListener(new listen());
            options1.add(colorButton);

            //Second panel/line in options GUI
            fillBox=new JCheckBox("Filled");
            lineWidth=new JLabel("Line width:");
            lineText=new JTextField("5.0",4);
            
            options2.add(fillBox);
            options2.add(lineWidth);
            options2.add(lineText);
            
            //Options panel to hold undo and clear buttons
            undoButton=new JButton("Undo");
            undoButton.addActionListener(new listen());
            clearButton=new JButton("Clear");
            clearButton.addActionListener(new listen());
            options3.add(undoButton);
            options3.add(clearButton);
            
            //Add to main options panel
            add(options1);
            add(options2);
            add(options3);
        }
    }
    
    //Innerclass that will hold paintComponent method and act as a drawing panel
    private class drawPanel extends JPanel{
        private drawPanel(){
            //set size, color, and mouse listener
            setPreferredSize(new Dimension(600,400));
            setBackground(Color.WHITE);
            setOpaque(true);
            mouse move=new mouse();
            //Add mouselistener and mousemotionlistener
            addMouseMotionListener(move);
            addMouseListener(move);
        }
        
        //Paint method using graphics2d that just calls paint methods in the 
        //objects classes from objects stored in array lists.
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d=(Graphics2D) g;
            //Default stroke width
            g2d.setStroke(new BasicStroke(5.0f));
            
            //Check for valid points in both click and release mouse actions
            if (clickPoint!=null&&releasePoint!=null){
                //Run through all the array lists of shape objects
                //and draw any object it finds
                for (Rectangles r: rectArray){
                    r.paint(g2d);
                }
                for (Circles c:circArray){
                    c.paint(g2d);
                }
                for (Lines l:lineArray){
                    l.paint(g2d);
                }
                for (Triangles t:triArray){
                    t.paint(g2d);
                }
                for(Squares s:sqArray){
                    s.paint(g2d);
                }
                for (Ovals o:ovArray){
                    o.paint(g2d);
                }
            }
        }
    }
    
    //Inner action listener class
    public class listen implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            //check if color button is clicked then allow to choose new color
            //from jcolorchooser
            if (e.getSource()==colorButton){
                col=JColorChooser.showDialog(null,"Pick a Color",col);
                colorButton.setBackground(col);
            }
            
            //check for undo button or undo menu item
            else if(e.getSource()==undoButton||e.getSource()==undoItem){
                //check if there are any shape objects to undo
                if (holder.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Nothing left to undo!");
                }
                //Compare each shape with what is stored in holder arraylist
                //then delete that shape from the corresponding shape arraylist
                else if (holder.get(holder.size()-1).equals("Rectangle")){
                    rectArray.remove(rectArray.get(rectArray.size()-1));
                    holder.remove(holder.get(holder.size()-1));
                }
                else if (holder.get(holder.size()-1).equals("Circle")){
                    circArray.remove(circArray.get(circArray.size()-1));
                    holder.remove(holder.get(holder.size()-1));
                }
                else if (holder.get(holder.size()-1).equals("Line")){
                    lineArray.remove(lineArray.get(lineArray.size()-1));
                    holder.remove(holder.get(holder.size()-1));
                }
                else if (holder.get(holder.size()-1).equals("Triangle")){
                    triArray.remove(triArray.get(triArray.size()-1));
                    holder.remove(holder.get(holder.size()-1));
                }
                else if (holder.get(holder.size()-1).equals("Oval")){
                    ovArray.remove(ovArray.get(ovArray.size()-1));
                    holder.remove(holder.get(holder.size()-1));
                }
                else if (holder.get(holder.size()-1).equals("Square")){
                    sqArray.remove(sqArray.get(sqArray.size()-1));
                    holder.remove(holder.get(holder.size()-1));
                }
                //repaint to remove last shape
                test.repaint();
                //reset text area
                textArea.setText("Draw a shape!");
            }
            
            //Actions for shape items and change combo box selection
            else if(e.getSource()==rectangleItem){
                shapesCombo.setSelectedItem("Rectangle");
            }
            else if(e.getSource()==circleItem){
                shapesCombo.setSelectedItem("Circle");
            }
            else if(e.getSource()==lineItem){
                shapesCombo.setSelectedItem("Line");
            }
            else if(e.getSource()==triangleItem){
                shapesCombo.setSelectedItem("Triangle");
            }
            else if(e.getSource()==ovalItem){
                shapesCombo.setSelectedItem("Oval");
            }
            else if(e.getSource()==squareItem){
                shapesCombo.setSelectedItem("Square");
            }
            
            //clear all shapes from arraylists and reset text area
            else if (e.getSource()==clearButton||e.getSource()==clearItem){
                rectArray.clear();
                circArray.clear();
                lineArray.clear();
                triArray.clear();
                ovArray.clear();
                sqArray.clear();
                holder.clear();
                test.repaint();
                textArea.setText("Draw a shape!");
            }
            //Check for color menu item and change text area font color
            else if(e.getSource()==blackItem)
                textArea.setForeground(Color.BLACK);     
            else if(e.getSource()==blueItem)
                textArea.setForeground(Color.BLUE);
            else if(e.getSource()==greenItem)
                textArea.setForeground(Color.GREEN);
            else if (e.getSource()==redItem)
                textArea.setForeground(Color.RED);
            
            //Check for font actions and change font to selected font
            else if(e.getSource()==serifItem){
                f=new Font("Serif",f.getStyle(),f.getSize());
                textArea.setFont(f);
            }
            else if (e.getSource()==sansItem){
                f=new Font("SansSerif",f.getStyle(),f.getSize());
                textArea.setFont(f);
            }
            else if(e.getSource()==monoItem){
                f=new Font("Monospaced",f.getStyle(),f.getSize());
                textArea.setFont(f);
            }
            
            //Check to see if bold or italics is selected and change font
            else if(e.getSource()==italicItem){
                if(boldItem.isSelected())
                    f=new Font(f.getName(),Font.BOLD+Font.ITALIC,f.getSize());
                else
                    f=new Font(f.getName(),Font.ITALIC,f.getSize());
                textArea.setFont(f);
            }
            else if(e.getSource()==boldItem){
                if(italicItem.isSelected())
                    f=new Font(f.getName(),Font.BOLD+Font.ITALIC,f.getSize());
                else
                    f=new Font(f.getName(),Font.BOLD, f.getSize());
                textArea.setFont(f);
            }
            
            //exit menu item
            else if(e.getSource()==exitItem){
                System.exit(0);
            }
            //background menu item actions and change text area background color
            else if(e.getSource()==whiteItem)
                textArea.setBackground(Color.WHITE);
            else if(e.getSource()==cyanItem)
                textArea.setBackground(Color.CYAN);
            else if(e.getSource()==yellowItem)
                textArea.setBackground(Color.YELLOW);
            else if(e.getSource()==lgrayItem)
                textArea.setBackground(Color.LIGHT_GRAY);
        }
    }
    
    //Inner class for mouse listener using mouse adapter
    public class mouse extends MouseAdapter{
        
        //Method to get coordinates of cursor on drawing panel
        @Override
        public void mouseMoved(MouseEvent e){
            cursorLocation=new Points(e.getX(),e.getY());
            cursor.setText(cursorLocation.toString());
        }
        
        //Method for when mouse is pressed and save coordinates
        @Override
        public void mousePressed(MouseEvent e){
            clickPoint=new Points(e.getX(),e.getY());
        }
        
        //Method for when mouse is released and change coordinates
        @Override
        public void mouseReleased(MouseEvent e){
            releasePoint=new Points(e.getX(),e.getY());

            //Change points value if user clicks and releases on 
            //bottom-left and top-right corners so it will always draw
            //shape in box made by dragging mouse
            
            //Change if click bottom-left to top-right
            if(releasePoint.getY()<clickPoint.getY()&&
                    releasePoint.getX()>clickPoint.getX()){
                x1=clickPoint.getX();
                y1=releasePoint.getY();
                x2=releasePoint.getX();
                y2=clickPoint.getY();
            }
            
            //Change if user clicks top-right to bottom-left
            else if(clickPoint.getY()<releasePoint.getY()&&
                    clickPoint.getX()>releasePoint.getX()){
                x1=releasePoint.getX();
                y1=clickPoint.getY();
                x2=clickPoint.getX();
                y2=releasePoint.getY();
            }
            
            //Else, top-left and bottom-right corners are clicked
            else{
                x1=clickPoint.getX();
                y1=clickPoint.getY();
                x2=releasePoint.getX();
                y2=releasePoint.getY();
            }
            
            //Input validation for line width and then draw shape logic
            try{
                lineStroke=Float.parseFloat(lineText.getText());
                
                //Create shape object using coordinates from mouse click+release
                //using line width and fill boolean, then save to arraylist
                //Add shape to general array list for history of all shapes
                //drawn.
                if (shapesCombo.getSelectedItem()=="Rectangle"){
                    rectArray.add(new Rectangles(x1,y1,x2,y2,col,
                            fillBox.isSelected(),lineStroke));
                    holder.add("Rectangle");
                    textArea.setText(rectArray.get(rectArray.size()-1).toString());
                }
                else if (shapesCombo.getSelectedItem()=="Circle"){
                    circArray.add(new Circles(x1,y1,x2,y2,col,
                            fillBox.isSelected(),lineStroke));
                    holder.add("Circle");
                    textArea.setText(circArray.get(circArray.size()-1).toString());
                }
                
                //Only shape that uses original click points to draw lines so 
                //they don't get flipped if drawn other direction
                else if (shapesCombo.getSelectedItem()=="Line"){
                    lineArray.add(new Lines(clickPoint.getX(),clickPoint.getY(),
                            releasePoint.getX(),releasePoint.getY(),col,
                    fillBox.isSelected(),lineStroke));
                    holder.add("Line");
                    textArea.setText(lineArray.get(lineArray.size()-1).toString());
                }
                else if(shapesCombo.getSelectedItem()=="Triangle"){
                    triArray.add(new Triangles(x1,y1,x2,y2,col,
                            fillBox.isSelected(),lineStroke));
                    holder.add("Triangle");
                    textArea.setText(triArray.get(triArray.size()-1).toString());
                }
                else if (shapesCombo.getSelectedItem()=="Square"){
                    sqArray.add(new Squares(x1,y1,x2,y2,col,
                        fillBox.isSelected(),lineStroke));
                    holder.add("Square");
                    textArea.setText(sqArray.get(sqArray.size()-1).toString());
                }
                else if(shapesCombo.getSelectedItem()=="Oval"){
                    ovArray.add(new Ovals(x1,y1,x2,y2,col,
                        fillBox.isSelected(),lineStroke));
                    holder.add("Oval");
                    textArea.setText(ovArray.get(ovArray.size()-1).toString());
                }
                
                //repaint the drawing panel
                test.repaint();
            }
            //error message if it can't convert line width text field
            catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(null,"Please enter a positive number"
                        + " for line width.");
                lineText.setText("5.0");
            }
        }
    }
}
