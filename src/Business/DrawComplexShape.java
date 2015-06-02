package Business;

/**
 * Created by Paul on 18/05/2015.
 */

public class DrawComplexShape implements UndoableCommand {

    private Shape complexShape;

    public DrawComplexShape(Shape complexShape) {
        setComplexShape(complexShape);
    }

    @Override
    public void execute() {
        complexShape.draw();
    }

    public void setComplexShape(Shape complexShape) {
        this.complexShape = complexShape;
    }
}
