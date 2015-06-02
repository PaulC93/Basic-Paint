package Business;

import java.awt.geom.Point2D;

/**
 * Created by Paul on 17/05/2015.
 */
public class DrawPoint implements Command {

    private MyPoint point;

    public DrawPoint(Point2D point2D)
    {
        this.point=new MyPoint(point2D);
    }

    @Override
    public void execute() {
        point.draw();
    }
}
