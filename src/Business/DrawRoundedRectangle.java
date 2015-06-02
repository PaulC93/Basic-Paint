package Business;

import java.awt.geom.Point2D;

/**
 * Created by Paul on 17/05/2015.
 */
public class DrawRoundedRectangle implements UndoableCommand {

    private RoundedRectangle roundedRectangle;

    public DrawRoundedRectangle(Point2D start, Point2D end) {
        roundedRectangle=new RoundedRectangle(start,end);
    }

    @Override
    public void execute() {
        roundedRectangle.draw();
    }
}
