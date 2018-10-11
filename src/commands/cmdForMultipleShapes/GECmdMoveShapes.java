package commands.cmdForMultipleShapes;

import commands.cmdForSingleShape.GECmdMoveShape;
import shapes.GEShape;

import java.util.ArrayList;

public class GECmdMoveShapes extends GECmdForShapeList {
    public GECmdMoveShapes(ArrayList<GEShape> shapeList, ArrayList<GEShape> selectedShapes, ArrayList<GEShape> movedShapes) {
        super(shapeList, selectedShapes);

        for (int i = 0; i < this.selectedShapes.size(); i++)
            cmdList.add(new GECmdMoveShape(this.shapeList, this.selectedShapes.get(i), movedShapes.get(i)));
    }

    @Override
    public String toString() {
        return "이동";
    }
}
