package shapes;

import utils.GEAnchorList;

import java.awt.*;

public class GERectangle extends GEShape {
    public GERectangle() {
        super(new Rectangle());
    }

    public GERectangle(Shape shape, Point startP, GEAnchorList anchorList, Color lineColor, Color fillColor, BasicStroke stroke) { // added
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
        Rectangle rectangle = (Rectangle)myShape;
        rectangle.setFrame(startP.x, startP.y, currentP.x - startP.x, currentP.y - startP.y);

        if (anchorList != null) anchorList.setPosition(myShape.getBounds());
    }

    @Override
    public GEShape clone() {
        return new GERectangle();
    }

    @Override
    public GEShape duplicate() { // added
        return new GERectangle(myShape, startP, anchorList, lineColor, fillColor, stroke);
    }
}
