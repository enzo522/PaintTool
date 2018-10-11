package commands.cmdForMultipleShapes;

import commands.GECommand;
import shapes.GEShape;

import java.util.ArrayList;

public abstract class GECmdForMultipleShapes extends GECommand {
    protected ArrayList<GEShape> selectedShapes;
    protected ArrayList<GECommand> cmdList;

    public GECmdForMultipleShapes(ArrayList<GEShape> selectedShapes) {
        this.selectedShapes = new ArrayList<GEShape>();
        this.cmdList = new ArrayList<GECommand>();

        for (GEShape shape : selectedShapes) this.selectedShapes.add(shape);
    }

    @Override
    public void execute() {
        for (GECommand cmd : cmdList) cmd.execute();
    }

    @Override
    public void undo() {
        for (GECommand cmd : cmdList) cmd.undo();
    }

    @Override
    public abstract String toString();
}