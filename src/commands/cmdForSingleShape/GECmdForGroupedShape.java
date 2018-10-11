package commands.cmdForSingleShape;

import commands.cmdForMultipleShapes.GECmdForMultipleShapes;
import shapes.GEGroupedShape;

public abstract class GECmdForGroupedShape extends GECmdForSingleShape {
    protected GECmdForMultipleShapes cmd;

    public GECmdForGroupedShape(GEGroupedShape groupedShape) {
        super(groupedShape);
    }
}
