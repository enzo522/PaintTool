package commands.cmdForPanel;

import constants.GEConstants;
import frames.GEDrawingPanel;

import java.awt.*;

public class GECmdSetPanelLineColor extends GECmdForPanel {
    public GECmdSetPanelLineColor(GEDrawingPanel drawingPanel, Color newLineColor) {
        super(drawingPanel, drawingPanel.getLineColor(), newLineColor);
    }

    @Override
    public void execute() {
        drawingPanel.setPanelLineColor(newColor);
    }

    @Override
    public void undo() {
        drawingPanel.setPanelLineColor(oldColor);
    }

    @Override
    public String toString() {
        return newColor == GEConstants.DEFAULT_LINE_COLOR ? "패널 선 색 초기화" : "패널 선 색 변경";
    }
}
