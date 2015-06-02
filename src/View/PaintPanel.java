package View;

import Business.*;
import Business.Rectangle;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class PaintPanel extends JPanel implements DropTargetListener, MouseListener{

    private String chosenShape;
    private Point2D start,end,control1,control2;
    private int pointCount;
    private boolean simpleShapeSelected,readyToDraw, createComplexShape;
    private Business.Shapes complexShape;

    public PaintPanel()
    {
        // Create the DropTarget and register
        // it with the JPanel.
       DropTarget dropTarget = new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE,
                this, true, null);
        addMouseListener(this);
        readyToDraw=false;
        simpleShapeSelected =false;
        pointCount=1;
        createComplexShape=false;
        complexShape=new Shapes();
    }

   private static ArrayList<java.awt.Shape> shapes=new ArrayList<java.awt.Shape>();

    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        // paint background
        g2D.setBackground(Color.WHITE);
        g2D.clearRect(0, 0, getWidth(), getHeight());

        // paint shapes
        for(java.awt.Shape shape:shapes)
            g2D.draw(shape);
    }

    public static void drawPoint(MyPoint point)
    {
        //. point
       //shapes.add(new Line2D.Double(point.getX(),point.getY(),point.getX(),point.getY()));

        //x point
        shapes.add(new Line2D.Double(point.getX()-2,point.getY()-2,point.getX()+2,point.getY()+2));
        shapes.add(new Line2D.Double(point.getX()-2,point.getY()+2,point.getX()+2,point.getY()-2));
    }

    public static void drawLine(Line line)
    {
        Line2D line2D=new Line2D.Double(line.getStart(),line.getEnd());
        shapes.add(line2D);
    }

    public static void drawRectangle(Rectangle rectangle)
    {
        double width=rectangle.getEnd().getX()-rectangle.getStart().getX();
        double height=rectangle.getEnd().getY()-rectangle.getStart().getY();
        Rectangle2D rectangle2D=new Rectangle2D.Double(rectangle.getStart().getX(),rectangle.getStart().getY(),width, height);
        shapes.add(rectangle2D);
    }

    public static void drawEllipse(Ellipse ellipse) {
        double width=ellipse.getEnd().getX()-ellipse.getStart().getX();
        double height=ellipse.getEnd().getY()-ellipse.getStart().getY();
        Ellipse2D ellipse2D=new Ellipse2D.Double(ellipse.getStart().getX(),ellipse.getStart().getY(),width,height);
        shapes.add(ellipse2D);
    }

    public static void drawSimpleCurve(SimpleCurve simpleCurve) {

        QuadCurve2D quadCurve2D=new QuadCurve2D.Double(simpleCurve.getStart().getX(),simpleCurve.getStart().getY(),
                simpleCurve.getCtrl().getX(),simpleCurve.getCtrl().getY(),
                simpleCurve.getEnd().getX(),simpleCurve.getEnd().getY());
        shapes.add(quadCurve2D);
    }

    public static void drawDoubleCurve(DoubleCurve doubleCurve) {
        CubicCurve2D cubicCurve2D=new CubicCurve2D.Double(doubleCurve.getStart().getX(),doubleCurve.getStart().getY(),
                doubleCurve.getCtrl().getX(),doubleCurve.getCtrl().getY(),
                doubleCurve.getCtrl2().getX(),doubleCurve.getCtrl2().getY(),
                doubleCurve.getEnd().getX(),doubleCurve.getEnd().getY());
        shapes.add(cubicCurve2D);
    }

    public static void drawRoundedRectangle(RoundedRectangle rectangle) {
        double width=rectangle.getEnd().getX()-rectangle.getStart().getX();
        double height=rectangle.getEnd().getY()-rectangle.getStart().getY();
        RoundRectangle2D roundRectangle2D=new RoundRectangle2D.Double(rectangle.getStart().getX(),rectangle.getStart().getY(),
                width,height,rectangle.getArcWidth(),rectangle.getArcHeight());
        shapes.add(roundRectangle2D);
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {

    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragExit(DropTargetEvent dte) {

    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        dtde.acceptDrop(dtde.getDropAction());
        Transferable transferable = dtde.getTransferable();
        try {
            chosenShape = (String) transferable.getTransferData(new DataFlavor("application/x-java-jvm-local-objectref; class=java.lang.String"));
            if(!chosenShape.equals("Complex Shape *"))
            {
                simpleShapeSelected =true;
                JOptionPane.showMessageDialog(this, "Drawing shape, please choose the start point, end point, and control points if needed");
            }
            else {
                simpleShapeSelected = false;
                if(complexShape.isEmpty()) JOptionPane.showMessageDialog(this,"No complex shape defined");
                CommandManager.executeCommand(new DrawComplexShape(complexShape));
                this.repaint();
                this.setBounds(200, 0, this.getSize().width, this.getSize().height);
            }
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(simpleShapeSelected) {
            if (pointCount == 1) {
                start = new Point2D.Double(e.getX(), e.getY());
                CommandManager.executeCommand(new DrawPoint(start));
                readyToDraw = false;
                pointCount++;
            } else if (pointCount == 2) {
                end = new Point2D.Double(e.getX(), e.getY());
                CommandManager.executeCommand(new DrawPoint(end));
                if (chosenShape.equals("Line |") || chosenShape.equals("Rectangle []") || chosenShape.equals("Ellipse O") || chosenShape.equals("Rounded Rectangle ()"))
                    readyToDraw = true;
                else readyToDraw = false;
                pointCount++;
            } else if (pointCount == 3) {
                control1 = new Point2D.Double(e.getX(), e.getY());
                CommandManager.executeCommand(new DrawPoint(control1));
                if (chosenShape.equals("Simple Curve C"))
                    readyToDraw = true;
                else readyToDraw = false;
                pointCount++;
            } else if (pointCount == 4) {
                control2 = new Point2D.Double(e.getX(), e.getY());
                CommandManager.executeCommand(new DrawPoint(control2));
                if (chosenShape.equals("Double Curve S"))
                    readyToDraw = true;
                else readyToDraw = false;
                pointCount = 1;
            }


            if (readyToDraw) {
                if (chosenShape.equals("Line |")) {
                    CommandManager.executeCommand(new DrawLine(start, end));
                    if (createComplexShape)
                        complexShape.add(new Line(start,end));
                }else if (chosenShape.equals("Rectangle []")) {
                    CommandManager.executeCommand(new DrawRectangle(start, end));
                    if (createComplexShape)
                        complexShape.add(new Rectangle(start,end));
                } else if (chosenShape.equals("Ellipse O")) {
                    CommandManager.executeCommand(new DrawEllipse(start, end));
                    if (createComplexShape)
                        complexShape.add(new Ellipse(start,end));
                }else if (chosenShape.equals("Simple Curve C")) {
                    CommandManager.executeCommand(new DrawSimpleCurve(start, control1, end));
                    if (createComplexShape)
                        complexShape.add(new SimpleCurve(start, control1, end));
                } else if (chosenShape.equals("Double Curve S")) {
                    CommandManager.executeCommand(new DrawDoubleCurve(start, control1, control2, end));
                    if (createComplexShape)
                        complexShape.add(new DoubleCurve(start, control1, control2, end));
                } else if (chosenShape.equals("Rounded Rectangle ()")) {
                    CommandManager.executeCommand(new DrawRoundedRectangle(start, end));
                    if (createComplexShape)
                        complexShape.add(new RoundedRectangle(start,end));
                }
                removePointers();
                readyToDraw = false;
                pointCount = 1;
            }

            this.repaint();
            this.setBounds(200, 0, this.getSize().width, this.getSize().height);

        }
    }

    private void removePointers() {
        //remove guiding points

        if(!chosenShape.equals("Complex Shape *")){
        shapes.remove(shapes.size()-2);
        shapes.remove(shapes.size()-2);
        if (chosenShape.equals("Simple Curve C")) shapes.remove(shapes.size()-2);
        if (chosenShape.equals("Double Curve S"))
        {
            shapes.remove(shapes.size()-2);
            shapes.remove(shapes.size()-2);
        }

        //if x point used
        shapes.remove(shapes.size()-2);
        shapes.remove(shapes.size()-2);
        if (chosenShape.equals("Simple Curve C")) shapes.remove(shapes.size()-2);
        if (chosenShape.equals("Double Curve S"))
        {
            shapes.remove(shapes.size() - 2);
            shapes.remove(shapes.size() - 2);
        }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void setInitialState() {
        shapes.clear();
    }

    public boolean isCreateComplexShape() {
        return createComplexShape;
    }

    public void createComplexShape(boolean create) {
        if (create) complexShape.clear();
        this.createComplexShape = create;
    }
}