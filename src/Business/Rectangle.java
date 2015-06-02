package Business;

import View.PaintPanel;

import java.awt.geom.Point2D;

/**
 * Created by Paul on 13/05/2015.
 */
public class Rectangle extends Line implements Shape {

    public Rectangle(){}

    public Rectangle(Point2D start, Point2D end)
    {
        setStart(start);
        setEnd(end);
    }

    @Override
    public void draw() {
      PaintPanel.drawRectangle(this);
    }
}
