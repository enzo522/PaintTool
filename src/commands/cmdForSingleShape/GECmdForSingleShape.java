package commands.cmdForSingleShape;

import commands.GECommand;
import shapes.GEShape;

public abstract class GECmdForSingleShape extends GECommand {
    protected GEShape selectedShape;

    public GECmdForSingleShape(GEShape selectedShape) {
        this.selectedShape = selectedShape;
    }
}