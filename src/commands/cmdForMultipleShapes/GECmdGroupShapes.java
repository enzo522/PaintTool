package commands.cmdForMultipleShapes;

import shapes.GEGroupedShape;
import shapes.GEShape;

import java.util.ArrayList;

public class GECmdGroupShapes extends GECmdForShapeList {
    private GEGroupedShape groupedShape;

    public GECmdGroupShapes(ArrayList<GEShape> shapeList, ArrayList<GEShape> selectedShapes) {
        super(shapeList, selectedShapes);

        groupedShape = new GEGroupedShape(selectedShapes);
    }

    @Override
    public void execute() {
        for (GEShape shape : selectedShapes) {
            shapeList.remove(shape);
            shape.setSelected(false);
        }

        groupedShape.setSelected(true);
        shapeList.add(groupedShape);
    }

    @Override
    public void undo() {
        shapeList.remove(groupedShape);
        groupedShape.setSelected(false);

        for (GEShape shape : selectedShapes) {
            shapeList.add(shape);
            shape.setSelected(true);
        }
    }

    @Override
    public String toString() {
        return "그룹화";
    }
}
