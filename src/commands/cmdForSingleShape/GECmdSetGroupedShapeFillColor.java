package commands.cmdForSingleShape;

import commands.cmdForMultipleShapes.GECmdSetShapesFillColor;
import shapes.GEGroupedShape;
import shapes.GEShape;

import java.awt.*;

public class GECmdSetGroupedShapeFillColor extends GECmdForGroupedShape {
    public GECmdSetGroupedShapeFillColor(GEGroupedShape groupedShape, Color newColor) {
        super(groupedShape);

        this.cmd = new GECmdSetShapesFillColor(((GEGroupedShape)this.selectedShape).getShapesToGroup(), newColor);
    }

    @Override
    public void execute() {
        cmd.execute();
        selectedShape.setSelected(true);

        for (GEShape shape : ((GEGroupedShape)selectedShape).getShapesToGroup()) shape.setSelected(false);
    }

    @Override
    public void undo() {
        cmd.undo();
        selectedShape.setSelected(true);

        for (GEShape shape : ((GEGroupedShape)selectedShape).getShapesToGroup()) shape.setSelected(false);
    }
}
