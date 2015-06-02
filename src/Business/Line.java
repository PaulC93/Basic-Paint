package Business;

import View.PaintPanel;

import java.awt.geom.Point2D;

/**
 * Created by Paul on 13/05/2015.
 */
public class Line implements Shape {

    private Point2D start,end;

    public Line(){}

    public Line(Point2D start, Point2D end) {
        setStart(start);
        setEnd(end);
    }

    @Override
    public void draw() {
        PaintPanel.drawLine(this);
    }

    public Point2D getStart() {
        return start;
    }

    public void setStart(Point2D start) {
        this.start = start;
    }

    public Point2D getEnd() {
        return end;
    }

    public void setEnd(Point2D end) {
        this.end = end;
    }
}
