package View;

import Business.CommandManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Paul on 17/05/2015.
 */
public class Toolbox extends JPanel {

    public Toolbox(PaintPanel paintPanel)
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JLabel lineLabel = new JLabel("Line |");
        JLabel rectangleLabel=new JLabel("Rectangle []");
        JLabel ellipseLabel=new JLabel("Ellipse O");
        JLabel simpleCurveLabel=new JLabel("Simple Curve C");
        JLabel doubleCurveLabel=new JLabel("Double Curve S");
        JLabel roundedRectangleLabel=new JLabel("Rounded Rectangle ()");
        JLabel complexShapeLabel=new JLabel("Complex Shape *");
        JLabel emptyLabel=new JLabel("--------------"); //for spacing purpose
        JButton createShapeButton=new JButton("Create complex shape");
        JButton saveShapeButton=new JButton("Save complex shape");
        JButton undoButton=new JButton("Undo");
        JButton redoButton=new JButton("Redo");
        JButton clearAllButton=new JButton("Clear All");

        TransferHandler textTransferHandler=new TransferHandler("text");

        lineLabel.setTransferHandler(textTransferHandler) ;
        rectangleLabel.setTransferHandler(textTransferHandler);
        ellipseLabel.setTransferHandler(textTransferHandler);
        simpleCurveLabel.setTransferHandler(textTransferHandler);
        doubleCurveLabel.setTransferHandler(textTransferHandler);
        roundedRectangleLabel.setTransferHandler(textTransferHandler);
        complexShapeLabel.setTransferHandler(textTransferHandler);

        ShapeMouseAdapter shapeMouseAdapter=new ShapeMouseAdapter();

        lineLabel.addMouseListener(shapeMouseAdapter);
        rectangleLabel.addMouseListener(shapeMouseAdapter);
        ellipseLabel.addMouseListener(shapeMouseAdapter);
        simpleCurveLabel.addMouseListener(shapeMouseAdapter);
        doubleCurveLabel.addMouseListener(shapeMouseAdapter);
        roundedRectangleLabel.addMouseListener(shapeMouseAdapter);
        complexShapeLabel.addMouseListener(shapeMouseAdapter);

        createShapeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintPanel.createComplexShape(true);
            }
        });

        saveShapeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintPanel.createComplexShape(false);
            }
        });

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommandManager.undo();
                paintPanel.repaint();
                paintPanel.setBounds(200, 0, paintPanel.getSize().width, paintPanel.getSize().height);
            }
        });

       redoButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               CommandManager.redo();
               paintPanel.repaint();
               paintPanel.setBounds(200, 0, paintPanel.getSize().width, paintPanel.getSize().height);
           }
       });

        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommandManager.clearAll();
                paintPanel.setInitialState();
                paintPanel.repaint();
                paintPanel.setBounds(200, 0,  paintPanel.getSize().width, paintPanel.getSize().height);
            }
        });

        this.add(lineLabel);
        this.add(rectangleLabel);
        this.add(ellipseLabel);
        this.add(simpleCurveLabel);
        this.add(doubleCurveLabel);
        this.add(roundedRectangleLabel);
        this.add(complexShapeLabel);
        this.add(emptyLabel);
        this.add(createShapeButton);
        this.add(saveShapeButton);
        this.add(undoButton);
        this.add(redoButton);
        this.add(clearAllButton);


    }

    private class ShapeMouseAdapter extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent e) {
            JComponent comp = (JComponent) e.getSource();
            TransferHandler th = comp.getTransferHandler();
            th.exportAsDrag(comp, e, TransferHandler.COPY);
        }
    }
}
