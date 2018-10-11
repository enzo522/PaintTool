package commands.cmdForSingleShape;

import shapes.GEShape;

import java.util.ArrayList;

public class GECmdResizeShape extends GECmdForSingleShape {
    private ArrayList<GEShape> shapeList;
    private GEShape resizedShape;

    public GECmdResizeShape(ArrayList<GEShape> shapeList, GEShape selectedShape, GEShape resizedShape) {
        super(selectedShape);

        this.shapeList = shapeList;
        this.resizedShape = resizedShape;
    }

    @Override
    public void execute() {
        shapeList.remove(selectedShape);
        shapeList.add(resizedShape);
        resizedShape.setSelected(true);
    }

    @Override
    public void undo() {
        shapeList.remove(resizedShape);
        shapeList.add(selectedShape);
        selectedShape.setSelected(true);
    }
}
