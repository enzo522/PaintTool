package frames;

import constants.GEConstants;
import menues.GEMenuBar;

import javax.swing.*;
import java.awt.*;

public class GEMainFrame extends JFrame {
    private static GEMainFrame uniqueMainFrame = new GEMainFrame(GEConstants.TITLE_MAINFRAME);
    private GEDrawingPanel drawingPanel;
    private GEMenuBar menuBar;
    private GEToolBar toolBar;

    public GEMainFrame(String title) {
        super(title);

        drawingPanel = new GEDrawingPanel();
        this.add(drawingPanel);

        menuBar = new GEMenuBar();
        this.setJMenuBar(menuBar);

        toolBar = new GEToolBar(GEConstants.TITLE_TOOLBAR);
        this.add(BorderLayout.NORTH, toolBar);
    }

    public static GEMainFrame getInstance() {
        return uniqueMainFrame;
    }

    public void init() {
        toolBar.init(drawingPanel);
        menuBar.init(drawingPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(GEConstants.WIDTH_MAINFRAME, GEConstants.HEIGHT_MAINFRAME);
        this.setVisible(true);
    }

    @Override
    public void repaint() { // added
        toolBar.updateUI();
        menuBar.updateUI();
    }
}
