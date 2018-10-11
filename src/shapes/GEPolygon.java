package shapes;

import utils.GEAnchorList;

import java.awt.*;

public class GEPolygon extends GEShape {
    public GEPolygon() {
        super(new Polygon());
    }

    public GEPolygon(Shape shape, Point startP, GEAnchorList anchorList, Color lineColor, Color fillColor, BasicStroke stroke) { // added
        super(shape);

        this.startP = startP;
        this.anchorList = anchorList;
        this.lineColor = lineColor;
        this.fillColor = fillColor;
        this.stroke = stroke;
    }

    @Override
    public void initDraw(Point startP) {
        ((Polygon)myShape).addPoint(startP.x, startP.y);
        this.startP = startP; // added
    }

    @Override
    public void setCoordinate(Point currentP) {
        Polygon polygon = (Polygon)myShape;
        polygon.xpoints[polygon.npoints - 1] = currentP.x;
        polygon.ypoints[polygon.npoints - 1] = currentP.y;

        if (anchorList != null) anchorList.setPosition(myShape.getBounds());
    }

    public void continueDrawing(Point currentP) {
        ((Polygon)myShape).addPoint(currentP.x, currentP.y);
        this.startP = new Point(getBounds().x, getBounds().y); // added
    }

    @Override
    public GEShape clone() {
        return new GEPolygon();
    }

    @Override
    public GEShape duplicate() { // added
        return new GEPolygon(myShape, startP, anchorList, lineColor, fillColor, stroke);
    }
}
