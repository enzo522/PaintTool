package commands.cmdForSingleShape;

import shapes.GEShape;

import java.awt.*;
import java.util.ArrayList;

public class GECmdPasteShape extends GECmdForSingleShape {
    private ArrayList<GEShape> shapeList;
    private Point oldPoint;
    private Point newPoint;

    public GECmdPasteShape(ArrayList<GEShape> shapeList, GEShape selectedShape, Point pastePoint) {
        super(selectedShape);

        this.shapeList = shapeList;
        this.oldPoint = new Point(selectedShape.getStartP().x - pastePoint.x,
                selectedShape.getStartP().y - pastePoint.y);
        this.newPoint = new Point(pastePoint.x - selectedShape.getStartP().x,
                pastePoint.y - selectedShape.getStartP().y);
    }

    @Override
    public void execute() {
        selectedShape.moveCoordinate(newPoint);
        shapeList.add(selectedShape);
        selectedShape.setSelected(true);
    }

    @Override
    public void undo() {
        selectedShape.moveCoordinate(oldPoint);
        shapeList.remove(selectedShape);
        selectedShape.setSelected(false);
    }
}
