package commands.cmdForPanel;

import commands.GECommand;
import frames.GEDrawingPanel;

import java.awt.*;

public abstract class GECmdForPanel extends GECommand {
    protected GEDrawingPanel drawingPanel;
    protected Color oldColor;
    protected Color newColor;

    public GECmdForPanel(GEDrawingPanel drawingPanel, Color oldColor, Color newColor) {
        this.drawingPanel = drawingPanel;
        this.oldColor = oldColor;
        this.newColor = newColor;
    }

    @Override
    public abstract String toString();
}
