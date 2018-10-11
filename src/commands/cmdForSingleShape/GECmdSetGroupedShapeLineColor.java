package commands.cmdForSingleShape;

import commands.cmdForMultipleShapes.GECmdSetShapesLineColor;
import shapes.GEGroupedShape;
import shapes.GEShape;

import java.awt.*;

public class GECmdSetGroupedShapeLineColor extends GECmdForGroupedShape {
    public GECmdSetGroupedShapeLineColor(GEGroupedShape groupedShape, Color newColor) {
        super(groupedShape);

        this.cmd = new GECmdSetShapesLineColor(((GEGroupedShape)this.selectedShape).getShapesToGroup(), newColor);
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
