package shapes;

import constants.GEConstants;
import utils.GEAnchorList;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public abstract class GEShape {
    protected Shape myShape;
    protected Point startP;
    protected boolean selected;
    protected GEAnchorList anchorList;
    protected GEConstants.EAnchorTypes selectedAnchor;
    protected Color fillColor;
    protected Color lineColor;
    protected AffineTransform affineTransform;
    protected BasicStroke stroke; // added

    public GEShape(Shape shape) {
        this.myShape = shape;
        anchorList = null;
        selected = false;

        lineColor = GEConstants.DEFAULT_LINE_COLOR; // added
        fillColor = GEConstants.DEFAULT_FILL_COLOR; // added

        affineTransform = new AffineTransform();
        stroke = new BasicStroke(GEConstants.DEFAULT_DASHEDLINE_WIDTH, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER); // added
    }

    public GEAnchorList getAnchorList() {
        return anchorList;
    }

    public GEConstants.EAnchorTypes getSelectedAnchor() {
        return selectedAnchor;
    }

    public void setSelectedAnchor(GEConstants.EAnchorTypes selectedAnchor) { // added
        this.selectedAnchor = selectedAnchor;
    }

    public void draw(Graphics2D g2D) {
        if (fillColor != null) {
            g2D.setColor(fillColor);
            g2D.fill(myShape);
        }

        if (lineColor != null) {
            g2D.setColor(lineColor);

            if (stroke != null) g2D.setStroke(stroke); // added

            g2D.draw(myShape);
        }

        if (selected) {
            g2D.setStroke(new BasicStroke(GEConstants.DEFAULT_DASHEDLINE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER)); // added
            anchorList.setPosition(myShape.getBounds());
            anchorList.draw(g2D);
        }
    }

    public GEConstants.EAnchorTypes onAnchor(Point p) {
        selectedAnchor = anchorList.onAnchors(p);

        return selectedAnchor;
    }

    public void moveCoordinate(Point moveP) {
        affineTransform.setToTranslation(moveP.getX(), moveP.getY());
        myShape = affineTransform.createTransformedShape(myShape);
        startP = new Point(getBounds().x, getBounds().y); // added
    }

    public void move(Point resizeAnchor) {
        affineTransform.setToTranslation(resizeAnchor.x, resizeAnchor.y);
        myShape = affineTransform.createTransformedShape(myShape);
    }

    public void moveReverse(Point resizeAnchor) {
        affineTransform.setToTranslation(-resizeAnchor.x, -resizeAnchor.y);
        myShape = affineTransform.createTransformedShape(myShape);
    }

    public void resizeCoordinate(Point2D resizeFactor) {
        affineTransform.setToScale(resizeFactor.getX(), resizeFactor.getY());
        myShape = affineTransform.createTransformedShape(myShape);
    }

    public Rectangle getBounds() { // added
        return myShape.getBounds();
    }

    public Color getLineColor() { // added
        return lineColor;
    }

    public Color getFillColor() { // added
        return fillColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Point getStartP() { // added
        return startP;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;

        if (selected) {
            anchorList = new GEAnchorList();
            anchorList.setPosition(myShape.getBounds());
        }
        else anchorList = null;
    }

    public void setStroke(BasicStroke newStroke) { // added
        stroke = newStroke;
    }

    public BasicStroke getStroke() { // added
        return stroke;
    }

    public boolean isSelected() { // added
        return selected;
    }

    public boolean isWorthAdding() { // added
        return myShape.getBounds().width > 0 && myShape.getBounds().height > 0;
    }

    public boolean onShape(Point p) {
        if (anchorList != null) {
            selectedAnchor = anchorList.onAnchors(p);

            if (selectedAnchor != GEConstants.EAnchorTypes.NONE) return true;
        }

        return myShape.intersects(new Rectangle(p.x, p.y, 10, 10));
    }

    public abstract void initDraw(Point startP);
    public abstract void setCoordinate(Point currentP);
    public abstract GEShape clone();
    public abstract GEShape duplicate(); // added
}
