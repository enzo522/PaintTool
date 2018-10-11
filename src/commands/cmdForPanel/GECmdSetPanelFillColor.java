package commands.cmdForPanel;

import constants.GEConstants;
import frames.GEDrawingPanel;

import java.awt.*;

public class GECmdSetPanelFillColor extends GECmdForPanel {
    public GECmdSetPanelFillColor(GEDrawingPanel drawingPanel, Color newFillColor) {
        super(drawingPanel, drawingPanel.getFillColor(), newFillColor);
    }

    @Override
    public void execute() {
        drawingPanel.setPanelFillColor(newColor);
    }

    @Override
    public void undo() {
        drawingPanel.setPanelFillColor(oldColor);
    }

    @Override
    public String toString() {
        return newColor == GEConstants.DEFAULT_FILL_COLOR ? "패널 면 색 초기화" : "패널 면 색 변경";
    }
}
