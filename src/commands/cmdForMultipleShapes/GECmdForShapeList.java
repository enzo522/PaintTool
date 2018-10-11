package commands.cmdForMultipleShapes;

import shapes.GEShape;

import java.util.ArrayList;

public abstract class GECmdForShapeList extends GECmdForMultipleShapes {
    protected ArrayList<GEShape> shapeList;

    public GECmdForShapeList(ArrayList<GEShape> shapeList, ArrayList<GEShape> selectedShapes) {
        super(selectedShapes);

        this.shapeList = shapeList;
    }
}
