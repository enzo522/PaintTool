package commands.cmdForSingleShape;

import shapes.GEShape;

import java.util.ArrayList;

public class GECmdAddShape extends GECmdForSingleShape {
    private ArrayList<GEShape> shapeList;

    public GECmdAddShape(ArrayList<GEShape> shapeList, GEShape selectedShape) {
        super(selectedShape);

        this.shapeList = shapeList;
    }

    @Override
    public void execute() {
        shapeList.add(selectedShape);
        selectedShape.setSelected(true);
    }

    @Override
    public void undo() {
        shapeList.remove(selectedShape);
        selectedShape.setSelected(false);
    }

    @Override
    public String toString() {
        return "추가";
    }
}
