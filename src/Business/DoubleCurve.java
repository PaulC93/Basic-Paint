package Business;

import View.PaintPanel;

import java.awt.geom.Point2D;

/**
 * Created by Paul on 17/05/2015.
 */
public class DoubleCurve extends SimpleCurve implements Shape{

    private Point2D ctrl2;

    public DoubleCurve(Point2D start, Point2D ctrl1, Point2D ctrl2, Point2D end)
    {
        setStart(start);
        setEnd(end);
        setCtrl(ctrl1);
        setCtrl2(ctrl2);
    }

    public Point2D getCtrl2() {
        return ctrl2;
    }

    public void setCtrl2(Point2D ctrl2) {
        this.ctrl2 = ctrl2;
    }

    @Override
    public void draw() {
        PaintPanel.drawDoubleCurve(this);
    }
}
