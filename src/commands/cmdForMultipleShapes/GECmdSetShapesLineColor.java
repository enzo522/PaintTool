package commands.cmdForMultipleShapes;

import commands.cmdForSingleShape.GECmdSetGroupedShapeLineColor;
import commands.cmdForSingleShape.GECmdSetShapeLineColor;
import constants.GEConstants;
import shapes.GEGroupedShape;
import shapes.GEShape;

import java.awt.*;
import java.util.ArrayList;

public class GECmdSetShapesLineColor extends GECmdForShapesColor {
    public GECmdSetShapesLineColor(ArrayList<GEShape> selectedShapes, Color newColor) {
        super(selectedShapes, newColor);

        for (GEShape shape : this.selectedShapes) {
            if (shape instanceof GEGroupedShape)
                cmdList.add(new GECmdSetGroupedShapeLineColor((GEGroupedShape)shape, newColor));
            else
                cmdList.add(new GECmdSetShapeLineColor(shape, newColor));
        }
    }

    @Override
    public String toString() {
        return newColor == GEConstants.DEFAULT_LINE_COLOR ? "도형 선 색 초기화" : "도형 선 색 변경";
    }
}
