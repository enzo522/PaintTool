package commands.cmdForSingleShape;

import commands.cmdForMultipleShapes.GECmdChangeShapesStroke;
import shapes.GEGroupedShape;
import shapes.GEShape;

import java.awt.*;

public class GECmdChangeGroupedShapeStroke extends GECmdForGroupedShape {
    public GECmdChangeGroupedShapeStroke(GEGroupedShape groupedShape, BasicStroke newStroke) {
        super(groupedShape);

        cmd = new GECmdChangeShapesStroke(((GEGroupedShape)this.selectedShape).getShapesToGroup(), newStroke);
    }

    public GECmdChangeGroupedShapeStroke(GEGroupedShape groupedShape, int newWidth) {
        super(groupedShape);

        cmd = new GECmdChangeShapesStroke(((GEGroupedShape)this.selectedShape).getShapesToGroup(), newWidth);
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
