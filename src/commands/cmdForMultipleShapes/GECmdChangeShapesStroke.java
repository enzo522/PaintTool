package commands.cmdForMultipleShapes;

import commands.cmdForSingleShape.GECmdChangeGroupedShapeStroke;
import commands.cmdForSingleShape.GECmdChangeShapeStroke;
import shapes.GEGroupedShape;
import shapes.GEShape;

import java.awt.*;
import java.util.ArrayList;

public class GECmdChangeShapesStroke extends GECmdForMultipleShapes {
    public GECmdChangeShapesStroke(ArrayList<GEShape> selectedShapes, BasicStroke newStroke) {
        super(selectedShapes);

        for (GEShape shape : this.selectedShapes) {
            if (shape instanceof GEGroupedShape)
                cmdList.add(new GECmdChangeGroupedShapeStroke((GEGroupedShape)shape, newStroke));
            else
                cmdList.add(new GECmdChangeShapeStroke(shape, newStroke));
        }
    }

    public GECmdChangeShapesStroke(ArrayList<GEShape> selectedShapes, int newWidth) {
        super(selectedShapes);

        for (GEShape shape : this.selectedShapes) {
            if (shape instanceof GEGroupedShape)
                cmdList.add(new GECmdChangeGroupedShapeStroke((GEGroupedShape)shape, newWidth));
            else
                cmdList.add(new GECmdChangeShapeStroke(shape, newWidth));
        }
    }

    @Override
    public String toString() {
        return "도형 선 속성 변경";
    }
}
