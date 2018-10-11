package commands.cmdForSingleShape;

import shapes.GEShape;

import java.util.ArrayList;

public class GECmdMoveShape extends GECmdForSingleShape {
    private ArrayList<GEShape> shapeList;
    private GEShape movedShape;

    public GECmdMoveShape(ArrayList<GEShape> shapeList, GEShape selectedShape, GEShape movedShape) {
        super(selectedShape);

        this.shapeList = shapeList;
        this.movedShape = movedShape;
    }

    @Override
    public void execute() {
        shapeList.remove(selectedShape);
        shapeList.add(movedShape);
        movedShape.setSelected(true);
    }

    @Override
    public void undo() {
        shapeList.remove(movedShape);
        shapeList.add(selectedShape);
        selectedShape.setSelected(true);
    }
}
