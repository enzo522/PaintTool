package commands.cmdForMultipleShapes;

import shapes.GEShape;

import java.awt.*;
import java.util.ArrayList;

public abstract class GECmdForShapesColor extends GECmdForMultipleShapes {
    protected Color newColor;

    public GECmdForShapesColor(ArrayList<GEShape> selectedShapes, Color newColor) {
        super(selectedShapes);

        this.newColor = newColor;
    }
}
