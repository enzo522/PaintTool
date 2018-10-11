package frames;

import constants.GEConstants;
import shapes.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GEToolBar extends JToolBar {
    private GEDrawingPanel drawingPanel;
    private GEToolBarHandler toolBarHandler;
    private ArrayList<JRadioButton> rButtons; // added

    public GEToolBar(String label) {
        super(label);

        toolBarHandler = new GEToolBarHandler();
        rButtons = new ArrayList<JRadioButton>(); // added
        ButtonGroup buttonGroup = new ButtonGroup();

        for (GEConstants.EToolBarButtons btn : GEConstants.EToolBarButtons.values()) {
            JRadioButton rButton = new JRadioButton();
            rButton.setIcon(new ImageIcon(GEConstants.IMG_URL + btn.toString() + GEConstants.TOOLBAR_BTN));
            rButton.setSelectedIcon(new ImageIcon(GEConstants.IMG_URL + btn.toString() + GEConstants.TOOLBAR_BTN_SELECT));

            rButton.setActionCommand(btn.toString());
            rButton.addActionListener(toolBarHandler);

            this.add(rButton);
            buttonGroup.add(rButton);
            rButtons.add(rButton); // added
        }
    }

    public void init(GEDrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
        this.clickDefaultButton();
    }

    @Override
    public void updateUI() { // added
        if (drawingPanel != null) {
            for (JRadioButton rButton : rButtons) rButton.setEnabled(drawingPanel.isIdle());
        }
    }

    private void clickDefaultButton() {
        JRadioButton rButton = (JRadioButton)this.getComponent(GEConstants.EToolBarButtons.Rectangle.ordinal());
        rButton.doClick();
    }

    private class GEToolBarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton selectedButton = (JRadioButton)e.getSource();

            if (selectedButton.getActionCommand().equals(GEConstants.EToolBarButtons.Rectangle.name()))
                drawingPanel.setCurrentShape(new GERectangle());
            else if (selectedButton.getActionCommand().equals(GEConstants.EToolBarButtons.Ellipse.name()))
                drawingPanel.setCurrentShape(new GEEllipse());
            else if (selectedButton.getActionCommand().equals(GEConstants.EToolBarButtons.Line.name()))
                drawingPanel.setCurrentShape(new GELine());
            else if (selectedButton.getActionCommand().equals(GEConstants.EToolBarButtons.Polygon.name()))
                drawingPanel.setCurrentShape(new GEPolygon());
            else if (selectedButton.getActionCommand().equals(GEConstants.EToolBarButtons.Select.name()))
                drawingPanel.setCurrentShape(null);
        }
    }
}
