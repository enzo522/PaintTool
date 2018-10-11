package commands.cmdForSingleShape;

import shapes.GEShape;

import java.awt.*;

public abstract class GECmdForShapeColor extends GECmdForSingleShape {
    protected Color oldColor;
    protected Color newColor;

    public GECmdForShapeColor(GEShape selectedShape, Color newColor) {
        super(selectedShape);

        this.newColor = newColor;
    }
}
