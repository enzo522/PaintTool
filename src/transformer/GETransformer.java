package transformer;

import constants.GEConstants;
import shapes.GEShape;

import java.awt.*;
import java.util.ArrayList;

public abstract class GETransformer {
    protected ArrayList<GEShape> shapes;
    protected GEShape shape;
    protected BasicStroke dashedLineStroke;

    public GETransformer(GEShape shape) {
        float dashes[] = { GEConstants.DEFAULT_DASH_OFFSET };
        this.shape = shape;
        this.dashedLineStroke = new BasicStroke(
                GEConstants.DEFAULT_DASHEDLINE_WIDTH,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                10, dashes, 0
        );
    }

    public GETransformer(ArrayList<GEShape> shapes) {
        this.shapes = new ArrayList<GEShape>();
        float dashes[] = { GEConstants.DEFAULT_DASH_OFFSET };
        this.dashedLineStroke = new BasicStroke(
                GEConstants.DEFAULT_DASHEDLINE_WIDTH,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                10, dashes, 0
        );

        for (GEShape shape : shapes) this.shapes.add(shape.duplicate());
    }

    public abstract void transformer(Graphics2D g2D, Point p);
    public abstract void init(Point p);
}
