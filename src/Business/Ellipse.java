package Business;

import View.PaintPanel;

import java.awt.geom.Point2D;

/**
 * Created by Paul on 17/05/2015.
 */
public class Ellipse extends Line implements Shape{

    public Ellipse(Point2D start, Point2D end)
    {
        setStart(start);
        setEnd(end);
    }

    @Override
    public void draw() {
        PaintPanel.drawEllipse(this);
    }
}
