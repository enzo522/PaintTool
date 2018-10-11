package commands.cmdForSingleShape;

import shapes.GEShape;

import java.util.ArrayList;

public class GECmdCutShape extends GECmdForSingleShape {
    private ArrayList<GEShape> shapeList;

    public GECmdCutShape(ArrayList<GEShape> shapeList, GEShape selectedShape) {
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
