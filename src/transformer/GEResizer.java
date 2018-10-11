package transformer;

import constants.GEConstants;
import shapes.GEShape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class GEResizer extends GETransformer {
    private ArrayList<Point> resizeAnchors;
    private ArrayList<Point> previousPs;

    public GEResizer(GEShape shape, ArrayList<GEShape> shapes) {
        super(shapes);

        this.resizeAnchors = new ArrayList<Point>();
        this.previousPs = new ArrayList<Point>();

        for (int i = 0; i < this.shapes.size(); i++) {
            this.shapes.get(i).setSelectedAnchor(shape.getSelectedAnchor());
            this.resizeAnchors.add(new Point());
            this.previousPs.add(new Point());
        }
    }

    @Override
    public void transformer(Graphics2D g2D, Point p) {
        for (int i = 0; i < shapes.size(); i++) {
            g2D.setXORMode(g2D.getBackground());
            g2D.setStroke(dashedLineStroke);
            Point2D resizeFactor = computeResizeFactor(shapes.get(i), previousPs.get(i), p);
            AffineTransform tempAffine = g2D.getTransform();
            g2D.translate(resizeAnchors.get(i).x, resizeAnchors.get(i).y);

            shapes.get(i).draw(g2D);
            shapes.get(i).resizeCoordinate(resizeFactor);
            shapes.get(i).draw(g2D);
            g2D.setTransform(tempAffine);
            previousPs.set(i, p);
        }
    }

    @Override
    public void init(Point p) {
        for (int i = 0; i < shapes.size(); i++) {
            previousPs.set(i, p);
            resizeAnchors.set(i, getResizeAnchor(shapes.get(i)));
            shapes.get(i).moveReverse(resizeAnchors.get(i));
        }
    }

    private Point getResizeAnchor(GEShape shape) {
        Point resizeAnchor = new Point();
        Ellipse2D.Double tempAnchor = null;

        if (shape.getSelectedAnchor() == GEConstants.EAnchorTypes.NW)
            tempAnchor = shape.getAnchorList().getAnchors().get(GEConstants.EAnchorTypes.SE.ordinal());
        else if (shape.getSelectedAnchor() == GEConstants.EAnchorTypes.NN)
            tempAnchor = shape.getAnchorList().getAnchors().get(GEConstants.EAnchorTypes.SS.ordinal());
        else if (shape.getSelectedAnchor() == GEConstants.EAnchorTypes.NE)
            tempAnchor = shape.getAnchorList().getAnchors().get(GEConstants.EAnchorTypes.SW.ordinal());
        else if (shape.getSelectedAnchor() == GEConstants.EAnchorTypes.WW)
            tempAnchor = shape.getAnchorList().getAnchors().get(GEConstants.EAnchorTypes.EE.ordinal());
        else if (shape.getSelectedAnchor() == GEConstants.EAnchorTypes.EE)
            tempAnchor = shape.getAnchorList().getAnchors().get(GEConstants.EAnchorTypes.WW.ordinal());
        else if (shape.getSelectedAnchor() == GEConstants.EAnchorTypes.SW)
            tempAnchor = shape.getAnchorList().getAnchors().get(GEConstants.EAnchorTypes.NE.ordinal());
        else if (shape.getSelectedAnchor() == GEConstants.EAnchorTypes.SS)
            tempAnchor = shape.getAnchorList().getAnchors().get(GEConstants.EAnchorTypes.NN.ordinal());
        else if (shape.getSelectedAnchor() == GEConstants.EAnchorTypes.SE)
            tempAnchor = shape.getAnchorList().getAnchors().get(GEConstants.EAnchorTypes.NW.ordinal());

        resizeAnchor.setLocation(tempAnchor.x, tempAnchor.y);

        return resizeAnchor;
    }

    private Point2D.Double computeResizeFactor(GEShape shape, Point previousP, Point currentP) {
        double deltaW = 0;
        double deltaH = 0;

        if (shape.getSelectedAnchor() == GEConstants.EAnchorTypes.NW) {
            deltaW =- (currentP.x - previousP.x);
            deltaH =- (currentP.y - previousP.y);
        } else if (shape.getSelectedAnchor() == GEConstants.EAnchorTypes.NN) {
            deltaW = 0;
            deltaH =- (currentP.y - previousP.y);
        } else if (shape.getSelectedAnchor() == GEConstants.EAnchorTypes.NE) {
            deltaW = currentP.x - previousP.x;
            deltaH =- (currentP.y - previousP.y);
        } else if (shape.getSelectedAnchor() == GEConstants.EAnchorTypes.WW) {
            deltaW =- (currentP.x - previousP.x);
            deltaH = 0;
        } else if (shape.getSelectedAnchor() == GEConstants.EAnchorTypes.EE) {
            deltaW = currentP.x - previousP.x;
            deltaH = 0;
        } else if (shape.getSelectedAnchor() == GEConstants.EAnchorTypes.SW) {
            deltaW =- (currentP.x - previousP.x);
            deltaH = currentP.y - previousP.y;
        } else if (shape.getSelectedAnchor() == GEConstants.EAnchorTypes.SS) {
            deltaW = 0;
            deltaH = currentP.y - previousP.y;
        } else if (shape.getSelectedAnchor() == GEConstants.EAnchorTypes.SE) {
            deltaW = currentP.x - previousP.x;
            deltaH = currentP.y - previousP.y;
        }

        double currentW = shape.getBounds().getWidth();
        double currentH = shape.getBounds().getHeight();
        double xFactor = 1.0;

        if (currentW > 0.0) xFactor = (1.0 + deltaW / currentW);

        double yFactor = 1.0;

        if (currentH > 0.0) yFactor = (1.0 + deltaH / currentH);

        return new Point2D.Double(xFactor, yFactor);
    }

    public ArrayList<GEShape> finalize(Point p) {
        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).move(resizeAnchors.get(i));
        }

        return shapes;
    }
}
