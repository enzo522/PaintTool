package commands.cmdForMultipleShapes;

import commands.cmdForSingleShape.GECmdPasteShape;
import shapes.GEShape;

import java.awt.*;
import java.util.ArrayList;

public class GECmdPasteShapes extends GECmdForShapeList {
    public GECmdPasteShapes(ArrayList<GEShape> shapeList, ArrayList<GEShape> selectedShapes, Point pastePoint) {
        super(shapeList, selectedShapes);

        for (GEShape shape : this.selectedShapes) cmdList.add(new GECmdPasteShape(this.shapeList, shape, pastePoint));
    }

    @Override
    public String toString() {
        return "붙이기";
    }
}
