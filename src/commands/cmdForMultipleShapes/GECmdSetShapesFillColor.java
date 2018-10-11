package commands.cmdForMultipleShapes;

import commands.cmdForSingleShape.GECmdSetGroupedShapeFillColor;
import commands.cmdForSingleShape.GECmdSetShapeFillColor;
import constants.GEConstants;
import shapes.GEGroupedShape;
import shapes.GEShape;

import java.awt.*;
import java.util.ArrayList;

public class GECmdSetShapesFillColor extends GECmdForShapesColor {
    public GECmdSetShapesFillColor(ArrayList<GEShape> selectedShapes, Color newColor) {
        super(selectedShapes, newColor);

        for (GEShape shape : this.selectedShapes) {
            if (shape instanceof GEGroupedShape)
                cmdList.add(new GECmdSetGroupedShapeFillColor((GEGroupedShape)shape, newColor));
            else
                cmdList.add(new GECmdSetShapeFillColor(shape, this.newColor));
        }
    }

    @Override
    public String toString() {
        return newColor == GEConstants.DEFAULT_FILL_COLOR ? "도형 면 색 초기화" : "도형 면 색 변경";
    }
}