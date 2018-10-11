package commands.cmdForMultipleShapes;

import commands.cmdForSingleShape.GECmdRemoveShape;
import shapes.GEShape;

import java.util.ArrayList;

public class GECmdRemoveShapes extends GECmdForShapeList {
    public GECmdRemoveShapes(ArrayList<GEShape> shapeList, ArrayList<GEShape> selectedShapes) {
        super(shapeList, selectedShapes);

        for (GEShape shape : this.selectedShapes) cmdList.add(new GECmdRemoveShape(this.shapeList, shape));
    }

    @Override
    public String toString() {
        return "삭제";
    }
}
