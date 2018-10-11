package menues;

import constants.GEConstants;
import frames.GEDrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GEMenuEdit extends JMenu {
    private EditMenuHandler editMenuHandler; // added
    private GEDrawingPanel drawingPanel; // added
    private ArrayList<JMenuItem> menuItems; // added

    public GEMenuEdit(String label) {
        super(label);

        editMenuHandler = new EditMenuHandler(); // added
        menuItems = new ArrayList<JMenuItem>(); // added

        for (GEConstants.EEditMenuItems btn : GEConstants.EEditMenuItems.values()) {
            JMenuItem menuItem = new JMenuItem(btn.toString()); // changed
            menuItem.addActionListener(editMenuHandler); // added
            menuItem.setActionCommand(btn.toString()); // added
            menuItem.setEnabled(false); // added
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
            for (JMenuItem item : menuItems) {
                item.setEnabled(false);

                if (drawingPanel.isIdle()) {
                    switch (GEConstants.EEditMenuItems.valueOf(item.getActionCommand())) {
                        case Undo:
                            item.setEnabled(drawingPanel.isUndoable());

                            if (drawingPanel.isUndoable())
                                item.setText(item.getActionCommand() + " (" + drawingPanel.getUndoName() + ")");
                            else item.setText(item.getActionCommand());
                            break;

                        case Redo:
                            item.setEnabled(drawingPanel.isRedoable());

                            if (drawingPanel.isRedoable())
                                item.setText(item.getActionCommand() + " (" + drawingPanel.getRedoName() + ")");
                            else item.setText(item.getActionCommand());
                            break;

                        case 삭제:
                            item.setEnabled(drawingPanel.isAnyShapeSelected());
                            break;

                        case 잘라내기:
                            item.setEnabled(drawingPanel.isAnyShapeSelected());
                            break;

                        case 복사:
                            item.setEnabled(drawingPanel.isAnyShapeSelected());
                            break;

                        case 붙이기:
                            item.setEnabled(drawingPanel.isPasteable());
                            break;

                        case Group:
                            item.setEnabled(drawingPanel.isGroupable());
                            break;

                        case Ungroup:
                            item.setEnabled(drawingPanel.isUngroupable());
                            break;

                        case 선속성변경:
                            item.setEnabled(drawingPanel.isAnyShapeSelected());
                            break;
                    }
                }
            }
        }
    }

    private void undo() { // added
        drawingPanel.undo();
    }

    private void redo() { // added
        drawingPanel.redo();
    }

    private void remove() { // added
        drawingPanel.removeSelectedShape();
    }

    private void cut() { // added
        drawingPanel.cutSelectedShape();
    }

    private void copy() { // added
        drawingPanel.copySelectedShape();
    }

    private void paste() { // added
        String x = JOptionPane.showInputDialog(null, "X 좌표");
        String y = JOptionPane.showInputDialog(null, "Y 좌표");

        if (x != null && y != null) {
            try {
                drawingPanel.pasteSelectedShape(new Point(Integer.parseInt(x), Integer.parseInt(y)));
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "좌표값으로 정수를 입력하세요.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void group() { // added
        drawingPanel.groupSelectedShape();
    }

    private void ungroup() { // added
        drawingPanel.ungroupSelectedShape();
    }

    private void changeStroke() { // added
        String[] toChange = { "종류", "두께" };
        Object selected = JOptionPane.showInputDialog(null, "변경할 속성을 선택하세요.", "", JOptionPane.PLAIN_MESSAGE, null, toChange, toChange[0]);
        Object newStroke = null;

        if (selected != null) {
            switch (selected.toString()) {
                case "종류":
                    String[] lineType = { "기본 실선", "기본 파선", "기본 1점 쇄선" };
                    Object selectedType = JOptionPane.showInputDialog(null, "종류를 선택하세요.", "", JOptionPane.PLAIN_MESSAGE, null, lineType, lineType[0]);

                    if (selectedType != null) {
                        switch (selectedType.toString()) {
                            case "기본 실선":
                                newStroke = new BasicStroke(GEConstants.DEFAULT_DASHEDLINE_WIDTH, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
                                break;

                            case "기본 파선":
                                float defaultDash[] = { GEConstants.DEFAULT_DASH_OFFSET };
                                newStroke = new BasicStroke(GEConstants.DEFAULT_DASHEDLINE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, defaultDash, 0);
                                break;

                            case "기본 1점 쇄선":
                                float dottedDash[] = { 10, 5, 5, 5 };
                                newStroke = new BasicStroke(GEConstants.DEFAULT_DASHEDLINE_WIDTH, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dottedDash, 0);
                                break;
                        }
                    }

                    break;

                case "두께":
                    String[] lineWidth = { "1", "2", "3", "4", "5" };
                    Object selectedWidth = JOptionPane.showInputDialog(null, "두께를 선택하세요.", "", JOptionPane.PLAIN_MESSAGE, null, lineWidth, lineWidth[0]);

                    if (selectedWidth != null) newStroke = Integer.parseInt(selectedWidth.toString());
                    break;
            }

            if (newStroke != null) drawingPanel.changeSelectedShapeStroke(newStroke);
        }
    }

    private class EditMenuHandler implements ActionListener { // added
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (GEConstants.EEditMenuItems.valueOf(e.getActionCommand())) {
                case Undo:
                    undo();
                    break;

                case Redo:
                    redo();
                    break;

                case 삭제:
                    remove();
                    break;

                case 잘라내기:
                    cut();
                    break;

                case 복사:
                    copy();
                    break;

                case 붙이기:
                    paste();
                    break;

                case Group:
                    group();
                    break;

                case Ungroup:
                    ungroup();
                    break;

                case 선속성변경:
                    changeStroke();
                    break;
            }
        }
    }
}
