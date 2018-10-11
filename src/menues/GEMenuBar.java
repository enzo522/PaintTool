package menues;

import constants.GEConstants;
import frames.GEDrawingPanel;

import javax.swing.*;

public class GEMenuBar extends JMenuBar {
    private GEMenuFile fileMenu;
    private GEMenuEdit editMenu;
    private GEMenuColor colorMenu;

    public GEMenuBar() {
        fileMenu = new GEMenuFile(GEConstants.TITLE_FILEMENU);
        editMenu = new GEMenuEdit(GEConstants.TITLE_EDITMENU);
        colorMenu = new GEMenuColor(GEConstants.TITLE_COLORMENU);

        this.add(fileMenu);
        this.add(editMenu);
        this.add(colorMenu);
    }

    public void init(GEDrawingPanel drawingPanel) {
        colorMenu.init(drawingPanel);
        editMenu.init(drawingPanel); // added
        fileMenu.init(drawingPanel); // added
    }

    @Override
    public void updateUI() { // added
        super.updateUI();

        for (int i = 0; i < this.getMenuCount(); i++) this.getMenu(i).updateUI();
    }
}
