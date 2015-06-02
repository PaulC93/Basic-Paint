package Business;

import java.util.ArrayList;

/**
 * Created by Paul on 13/05/2015.
 */
public class Shapes implements  Shape {

    private ArrayList<Shape> shapeList;

    public Shapes()
    {
        shapeList=new ArrayList<Shape>();
    }

    @Override
    public void draw() {
        for(Shape shape:shapeList)
            shape.draw();
    }

    public void add(Shape shape)
    {
        shapeList.add(shape);
    }

    public void remove(Shape shape)
    {
        shapeList.remove(shape);
    }

    public void clear() {
        shapeList.clear();
    }

    public boolean isEmpty() {
        return shapeList.isEmpty();
    }
}
