package Business;

import java.awt.geom.Point2D;

/**
 * Created by Paul on 17/05/2015.
 */
public class DrawDoubleCurve implements UndoableCommand {

    private DoubleCurve doubleCurve;

    public DrawDoubleCurve(Point2D start, Point2D control1, Point2D control2, Point2D end) {

        doubleCurve=new DoubleCurve(start,control1,control2,end);
    }

    @Override
    public void execute() {
        doubleCurve.draw();
    }
}
