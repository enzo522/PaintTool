package menues;

import constants.GEConstants;
import frames.GEDrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GEMenuColor extends JMenu {
    private ColorMenuHandler colorMenuHandler;
    private GEDrawingPanel drawingPanel;
    private ArrayList<JMenuItem> menuItems; // added

    public GEMenuColor(String label) {
        super(label);

        colorMenuHandler = new ColorMenuHandler();
        menuItems = new ArrayList<JMenuItem>(); // added

        for (GEConstants.EColorMenuItems btn : GEConstants.EColorMenuItems.values()) {
            JMenuItem menuItem = new JMenuItem(btn.toString());
            menuItem.addActionListener(colorMenuHandler);
            menuItem.setActionCommand(btn.toString());
            this.add(menuItem);
            menuItems.add(menuItem); // added
        }
    }

    public void init(GEDrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    @Override
    public void updateUI() { // added
        super.updateUI();

        if (menuItems != null && menuItems.size() > 0 && drawingPanel != null) {
            for (JMenuItem item : menuItems) item.setEnabled(drawingPanel.isIdle());
        }
    }

    private void setLineColor() {
        Color lineColor = JColorChooser.showDialog(null, GEConstants.TITLE_LINECOLOR, null);
        drawingPanel.setLineColor(lineColor);
    }

    private void setFillColor() {
        Color fillColor = JColorChooser.showDialog(null, GEConstants.TITLE_FILLCOLOR, null);
        drawingPanel.setFillColor(fillColor);
    }

    private void clearLineColor() {
        drawingPanel.setLineColor(GEConstants.DEFAULT_LINE_COLOR);
    }

    private void clearFillColor() {
        drawingPanel.setFillColor(GEConstants.DEFAULT_FILL_COLOR);
    }

    private class ColorMenuHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (GEConstants.EColorMenuItems.valueOf(e.getActionCommand())) {
                case SetLineColor:
                    setLineColor();
                    break;

                case SetFillColor:
                    setFillColor();
                    break;

                case ClearLineColor:
                    clearLineColor();
                    break;

                case ClearFillColor:
                    clearFillColor();
                    break;
            }
        }
    }
}
