package shapes;

import utils.GEAnchorList;

import java.awt.*;
import java.awt.geom.Line2D;

public class GELine extends GEShape {
    public GELine() {
        super(new Line2D.Double());
    }

    public GELine(Shape shape, Point startP, GEAnchorList anchorList, Color lineColor, BasicStroke stroke) { // added
        super(shape);

        this.startP = startP;
        this.anchorList = anchorList;
        this.lineColor = lineColor;
        this.stroke = stroke;
    }

    public void initDraw(Point startP) {
        this.startP = startP;
    }

    public void setCoordinate(Point currentP) {
        Line2D line = (Line2D)myShape;
        line.setLine(startP.x, startP.y, currentP.x, currentP.y);

        if (anchorList != null) anchorList.setPosition(myShape.getBounds());
    }

    @Override
    public GEShape clone() {
        return new GELine();
    }

    @Override
    public GEShape duplicate() { // added
        return new GELine(myShape, startP, anchorList, lineColor, stroke);
    }
}
