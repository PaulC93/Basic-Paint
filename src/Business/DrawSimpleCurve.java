package Business;

import java.awt.geom.Point2D;

/**
 * Created by Paul on 17/05/2015.
 */
public class DrawSimpleCurve implements UndoableCommand {

    private SimpleCurve simpleCurve;

    public DrawSimpleCurve(Point2D start, Point2D control1, Point2D end) {
        simpleCurve=new SimpleCurve(start,control1,end);
    }

    @Override
    public void execute() {
        simpleCurve.draw();
    }
}
