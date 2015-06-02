package Business;

import java.awt.geom.Point2D;

/**
 * Created by Paul on 13/05/2015.
 */
public class DrawRectangle implements UndoableCommand {

    private Rectangle rectangle;

    public DrawRectangle(Point2D start, Point2D end) {
        rectangle=new Rectangle(start,end);
    }

    @Override
    public void execute() {
       rectangle.draw();
    }

}
