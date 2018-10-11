package transformer;

import shapes.GEShape;

import java.awt.*;
import java.util.ArrayList;

public class GEMover extends GETransformer {
    private ArrayList<Point> previousPs;

    public GEMover(ArrayList<GEShape> shapes) {
        super(shapes);
        previousPs = new ArrayList<Point>();

        for (int i = 0; i < this.shapes.size(); i++) previousPs.add(new Point());
    }

    @Override
    public void transformer(Graphics2D g2D, Point p) {
        for (int i = 0; i < shapes.size(); i++) {
            Point tempP = new Point(p.x - previousPs.get(i).x, p.y - previousPs.get(i).y);
            g2D.setXORMode(g2D.getBackground());
            g2D.setStroke(dashedLineStroke);
            shapes.get(i).draw(g2D);
            shapes.get(i).moveCoordinate(tempP);
            shapes.get(i).draw(g2D);
            previousPs.set(i, p);
        }
    }

    @Override
    public void init(Point p) {
        for (int i = 0; i < previousPs.size(); i++) previousPs.set(i, p);
    }

    public ArrayList<GEShape> finalize(Point p) {
        return shapes;
    }
}
