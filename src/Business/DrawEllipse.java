package Business;

import Business.Command;

import java.awt.geom.Point2D;

/**
 * Created by Paul on 17/05/2015.
 */
public class DrawEllipse implements UndoableCommand {

    private Ellipse ellipse;

    public DrawEllipse(Point2D start, Point2D end) {
        ellipse=new Ellipse(start,end);
    }

    @Override
    public void execute() {
        ellipse.draw();
    }
}
