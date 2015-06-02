package Business;

import View.PaintPanel;

import java.awt.geom.Point2D;

/**
 * Created by Paul on 17/05/2015.
 */
public class RoundedRectangle extends Rectangle implements Shape {

    private double arcWidth, arcHeight;

    public RoundedRectangle(Point2D start, Point2D end)
    {
        setStart(start);
        setEnd(end);
       // setArcHeight(start.getY()-end.getY()/10.0);
      //  setArcWidth(start.getX()-end.getX()/10.0);
        setArcHeight(10.0);
        setArcWidth(10.0);
    }

    public void draw()
    {
        PaintPanel.drawRoundedRectangle(this);
    }

    public double getArcHeight() {
        return arcHeight;
    }

    public void setArcHeight(double arcHeight) {
        this.arcHeight = arcHeight;
    }

    public double getArcWidth() {
        return arcWidth;
    }

    public void setArcWidth(double arcWidth) {
        this.arcWidth = arcWidth;
    }

}
