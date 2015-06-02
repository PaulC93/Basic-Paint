package Business;

import java.awt.geom.Point2D;

/**
 * Created by Paul on 13/05/2015.
 */
public class DrawLine implements UndoableCommand {

    private Line line;

    public DrawLine(Point2D start, Point2D end)
    {
        line=new Line(start,end);
    }

    @Override
    public void execute() {
       line.draw();
    }

}
