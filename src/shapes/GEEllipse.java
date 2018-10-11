package shapes;

import utils.GEAnchorList;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GEEllipse extends GEShape {
    public GEEllipse() {
        super(new Ellipse2D.Double());
    }

    public GEEllipse(Shape shape, Point startP, GEAnchorList anchorList, Color lineColor, Color fillColor, BasicStroke stroke) { // added
        super(shape);

        this.startP = startP;
        this.anchorList = anchorList;
        this.lineColor = lineColor;
        this.fillColor = fillColor;
        this.stroke = stroke;
    }

    public void initDraw(Point startP) {
        this.startP = startP;
    }

    public void setCoordinate(Point currentP) {
        Ellipse2D ellipse = (Ellipse2D)myShape;
        ellipse.setFrame(startP.x, startP.y, currentP.x - startP.x, currentP.y - startP.y);

        if (anchorList != null) anchorList.setPosition(myShape.getBounds());
    }

    @Override
    public GEShape clone() {
        return new GEEllipse();
    }

    @Override
    public GEShape duplicate() { // added
        return new GEEllipse(myShape, startP, anchorList, lineColor, fillColor, stroke);
    }
}
