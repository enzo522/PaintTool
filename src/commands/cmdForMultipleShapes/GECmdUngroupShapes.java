package commands.cmdForMultipleShapes;

import shapes.GEGroupedShape;
import shapes.GEShape;

import java.util.ArrayList;

public class GECmdUngroupShapes extends GECmdForShapeList {
    private ArrayList<GEGroupedShape> groupedShapeList;

    public GECmdUngroupShapes(ArrayList<GEShape> shapeList, ArrayList<GEShape> selectedShapes) {
        super(shapeList, selectedShapes);

        this.groupedShapeList = new ArrayList<GEGroupedShape>();
    }

    @Override
    public void execute() {
        for (GEShape shape : selectedShapes) {
            if (shape instanceof GEGroupedShape) {
                groupedShapeList.add((GEGroupedShape)shape);
                shapeList.remove(shape);
                shape.setSelected(false);

                for (GEShape gShape : ((GEGroupedShape)shape).getShapesToGroup()) {
                    shapeList.add(gShape);
                    gShape.setSelected(true);
                }
            }
        }
    }

    @Override
    public void undo() {
        for (GEGroupedShape gShape : groupedShapeList) {
            for (GEShape shape : gShape.getShapesToGroup()) {
                shapeList.remove(shape);
                shape.setSelected(false);
            }
        }

        for (GEShape shape : selectedShapes) {
            shapeList.add(shape);
            shape.setSelected(true);
        }
    }

    @Override
    public String toString() {
        return "그룹 해제";
    }
}
