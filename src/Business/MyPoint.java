package Business;

import View.PaintPanel;

import java.awt.geom.Point2D;

/**
 * Created by Paul on 17/05/2015.
 */
public class MyPoint implements Shape {

    private double x,y;

    public MyPoint(Point2D point2D)
    {
        setX(point2D.getX());
        setY(point2D.getY());
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void draw() {
        PaintPanel.drawPoint(this);
    }
}
