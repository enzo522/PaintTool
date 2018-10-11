package commands.cmdForMultipleShapes;

import commands.IGEPasteable;
import commands.cmdForSingleShape.GECmdCutShape;
import shapes.GEShape;

import java.util.ArrayList;

public class GECmdCutShapes extends GECmdForShapeList implements IGEPasteable {
    public GECmdCutShapes(ArrayList<GEShape> shapeList, ArrayList<GEShape> selectedShapes) {
        super(shapeList, selectedShapes);

        for (GEShape shape : this.selectedShapes) cmdList.add(new GECmdCutShape(this.shapeList, shape));
    }

    @Override
    public ArrayList<GEShape> getShapesToPaste() {
        return selectedShapes;
    }

    @Override
    public String toString() {
        return "잘라내기";
    }
}
