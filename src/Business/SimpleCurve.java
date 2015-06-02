package Business;

import View.PaintPanel;
import java.awt.geom.Point2D;

/**
 * Created by Paul on 17/05/2015.
 */
public class SimpleCurve extends Line implements Shape{

    private Point2D ctrl;

    public SimpleCurve(){}

    public SimpleCurve(Point2D start, Point2D ctrl, Point2D end)
    {
        setStart(start);
        setEnd(end);
        setCtrl(ctrl);
    }


    public Point2D getCtrl() {
        return ctrl;
    }

    public void setCtrl(Point2D ctrl) {
        this.ctrl = ctrl;
    }


    @Override
    public void draw() {
        PaintPanel.drawSimpleCurve(this);
    }
}
