package commands.cmdForSingleShape;

import shapes.GEShape;

import java.util.ArrayList;

public class GECmdRemoveShape extends GECmdForSingleShape {
    private ArrayList<GEShape> shapeList;

    public GECmdRemoveShape(ArrayList<GEShape> shapeList, GEShape selectedShape) {
        super(selectedShape);

        this.shapeList = shapeList;
    }

    @Override
    public void execute() {
        shapeList.remove(selectedShape);
        selectedShape.setSelected(false);
    }

    @Override
    public void undo() {
        shapeList.add(selectedShape);
        selectedShape.setSelected(true);
    }
}
