package commands.cmdForMultipleShapes;

import commands.IGEPasteable;
import shapes.GEShape;

import java.util.ArrayList;

public class GECmdCopyShapes extends GECmdForMultipleShapes implements IGEPasteable {
    private ArrayList<GEShape> duplicatedShapes;

    public GECmdCopyShapes(ArrayList<GEShape> selectedShapes) {
        super(selectedShapes);

        duplicatedShapes = new ArrayList<GEShape>();
    }

    @Override
    public void execute() {
        for (GEShape shape : selectedShapes) {
            duplicatedShapes.add(shape.duplicate());
            shape.setSelected(true);
        }
    }

    @Override
    public void undo() {
        duplicatedShapes.clear();

        for (GEShape shape : selectedShapes)
            shape.setSelected(false);
    }

    @Override
    public ArrayList<GEShape> getShapesToPaste() {
        return duplicatedShapes;
    }

    @Override
    public String toString() {
        return "복사";
    }
}
