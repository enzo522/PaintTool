package commands.cmdForSingleShape;

import shapes.GEShape;

import java.awt.*;

public class GECmdChangeShapeStroke extends GECmdForSingleShape {
    private BasicStroke oldStroke;
    private BasicStroke newStroke;

    public GECmdChangeShapeStroke(GEShape selectedShape, BasicStroke newStroke) {
        super(selectedShape);

        this.oldStroke = this.selectedShape.getStroke();
        this.newStroke = newStroke;
    }

    public GECmdChangeShapeStroke(GEShape selectedShape, int newWidth) {
        super(selectedShape);

        this.oldStroke = this.selectedShape.getStroke();
        this.newStroke = new BasicStroke(newWidth, this.oldStroke.getEndCap(), this.oldStroke.getLineJoin(),
                this.oldStroke.getMiterLimit(), this.oldStroke.getDashArray(), this.oldStroke.getDashPhase());
    }

    @Override
    public void execute() {
        selectedShape.setStroke(newStroke);
        selectedShape.setSelected(true);
    }

    @Override
    public void undo() {
        selectedShape.setStroke(oldStroke);
        selectedShape.setSelected(true);
    }
}
