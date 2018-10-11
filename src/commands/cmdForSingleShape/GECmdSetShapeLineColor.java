package commands.cmdForSingleShape;

import shapes.GEShape;

import java.awt.*;

public class GECmdSetShapeLineColor extends GECmdForShapeColor {
    public GECmdSetShapeLineColor(GEShape selectedShape, Color newColor) {
        super(selectedShape, newColor);

        this.oldColor = selectedShape.getLineColor();
    }

    @Override
    public void execute() {
        selectedShape.setLineColor(newColor);
        selectedShape.setSelected(true);
    }

    @Override
    public void undo() {
        selectedShape.setLineColor(oldColor);
        selectedShape.setSelected(true);
    }
}
