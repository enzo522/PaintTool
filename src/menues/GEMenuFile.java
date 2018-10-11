package menues;

import constants.GEConstants;
import frames.GEDrawingPanel;

import javax.swing.*;
import java.util.ArrayList;

public class GEMenuFile extends JMenu {
    private GEDrawingPanel drawingPanel; // added
    private ArrayList<JMenuItem> menuItems; // added

    public GEMenuFile(String label) {
        super(label);

        menuItems = new ArrayList<JMenuItem>(); // added

        for (GEConstants.EFileMenuItems btn : GEConstants.EFileMenuItems.values()) {
            JMenuItem menuItem = new JMenuItem(btn.toString());
            this.add(menuItem);
            menuItems.add(menuItem); // added
        }
    }

    public void init(GEDrawingPanel drawingPanel) { // added
        this.drawingPanel = drawingPanel;
    }

    @Override
    public void updateUI() { // added
        super.updateUI();

        if (menuItems != null && menuItems.size() > 0 && drawingPanel != null) {
            for (JMenuItem item : menuItems) item.setEnabled(drawingPanel.isIdle());
        }
    }
}
