package commands.cmdForSingleShape;

import shapes.GEShape;

import java.awt.*;

public class GECmdSetShapeFillColor extends GECmdForShapeColor {
    public GECmdSetShapeFillColor(GEShape selectedShape, Color newColor) {
        super(selectedShape, newColor);

        this.oldColor = selectedShape.getFillColor();
    }

    @Override
    public void execute() {
        selectedShape.setFillColor(newColor);
        selectedShape.setSelected(true);
    }

    @Override
    public void undo() {
        selectedShape.setFillColor(oldColor);
        selectedShape.setSelected(true);
    }
}
