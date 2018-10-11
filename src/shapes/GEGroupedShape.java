package shapes;

import constants.GEConstants;
import utils.GEAnchorList;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class GEGroupedShape extends GEShape {
    private ArrayList<GEShape> shapesToGroup;

    public GEGroupedShape(ArrayList<GEShape> selectedShapes) {
        super(selectedShapes.get(0).myShape);
        shapesToGroup = new ArrayList<GEShape>();

        for (int i = 0; i < selectedShapes.size(); i++) {
            shapesToGroup.add(selectedShapes.get(i));
            myShape = myShape.getBounds().createUnion(shapesToGroup.get(i).getBounds());
        }

        startP = new Point(getBounds().x, getBounds().y);
        anchorList = new GEAnchorList();
        anchorList.setPosition(myShape.getBounds());
    }

    public ArrayList<GEShape> getShapesToGroup() {
        return shapesToGroup;
    }

    @Override
    public void draw(Graphics2D g2D) {
        for (GEShape shape : shapesToGroup) shape.draw(g2D);

        if (selected) {
            g2D.setStroke(new BasicStroke(GEConstants.DEFAULT_DASHEDLINE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
            anchorList.setPosition(myShape.getBounds());
            anchorList.draw(g2D);
        }
    }

    @Override
    public void moveCoordinate(Point moveP) {
        for (GEShape shape : shapesToGroup) shape.moveCoordinate(moveP);

        myShape = shapesToGroup.get(0).getBounds();

        for (GEShape shape : shapesToGroup) myShape = myShape.getBounds().createUnion(shape.getBounds());

        startP = new Point(getBounds().x, getBounds().y);
    }

    @Override
    public void move(Point resizeAnchor) {
        for (GEShape shape : shapesToGroup) shape.move(resizeAnchor);

        myShape = shapesToGroup.get(0).getBounds();

        for (GEShape shape : shapesToGroup) myShape = myShape.getBounds().createUnion(shape.getBounds());
    }

    @Override
    public void moveReverse(Point resizeAnchor) {
        for (GEShape shape : shapesToGroup) shape.moveReverse(resizeAnchor);

        myShape = shapesToGroup.get(0).getBounds();

        for (GEShape shape : shapesToGroup) myShape = myShape.getBounds().createUnion(shape.getBounds());
    }

    @Override
    public void resizeCoordinate(Point2D resizeFactor) {
        for (GEShape shape : shapesToGroup) shape.resizeCoordinate(resizeFactor);

        myShape = shapesToGroup.get(0).getBounds();

        for (GEShape shape : shapesToGroup) myShape = myShape.getBounds().createUnion(shape.getBounds());
    }

    @Override
    public boolean onShape(Point p) {
        if (anchorList != null) {
            selectedAnchor = anchorList.onAnchors(p);

            if (selectedAnchor != GEConstants.EAnchorTypes.NONE) return true;
        }

        for (GEShape shape : shapesToGroup) {
            if (shape.onShape(p)) return true;
        }

        return false;
    }

    @Override
    public void initDraw(Point startP) { }

    @Override
    public void setCoordinate(Point currentP) { }

    @Override
    public GEShape clone() {
        return null;
    }

    @Override
    public GEShape duplicate() { // added
        ArrayList<GEShape> newShapesToGroup = new ArrayList<GEShape>();

        for (GEShape shape : shapesToGroup) newShapesToGroup.add(shape.duplicate());

        return new GEGroupedShape(newShapesToGroup);
    }
}
