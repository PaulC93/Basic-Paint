package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Paul on 13/05/2015.
 */
public class GUI {

    private JFrame frame1;
    private Container pane;
    private Insets insets;
    private static final int HS = 10, VS = 15; //HS=HorizontalSpacing VS=VerticalSpacing

    public GUI() {
        // Set Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (UnsupportedLookAndFeelException e) {
        }

        // Create the frame
        frame1 = new JFrame("Basic Paint");
        frame1.setSize(800, 600);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pane=frame1.getContentPane();
        pane.setBackground(Color.cyan);
        pane.setLayout(new BorderLayout(10,10));
        frame1.setContentPane(pane);

        insets=pane.getInsets();

        PaintPanel paintPanel=new PaintPanel();
        Toolbox toolbox=new Toolbox(paintPanel);

        toolbox.setBounds(insets.left, insets.top, 200, 600);

        pane.add(toolbox);
        pane.add(paintPanel);

        frame1.setVisible(true);
        frame1.setLocationRelativeTo(null);

    }
}
