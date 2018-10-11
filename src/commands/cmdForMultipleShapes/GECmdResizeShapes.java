package commands.cmdForMultipleShapes;

import commands.cmdForSingleShape.GECmdResizeShape;
import shapes.GEShape;

import java.util.ArrayList;

public class GECmdResizeShapes extends GECmdForShapeList {
    public GECmdResizeShapes(ArrayList<GEShape> shapeList, ArrayList<GEShape> selectedShapes, ArrayList<GEShape> resizedShapes) {
        super(shapeList, selectedShapes);

        for (int i = 0; i < this.selectedShapes.size(); i++)
            cmdList.add(new GECmdResizeShape(this.shapeList, this.selectedShapes.get(i), resizedShapes.get(i)));
    }

    @Override
    public String toString() {
        return "크기 변경";
    }
}
