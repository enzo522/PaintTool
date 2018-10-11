package frames;

import commands.*;
import commands.cmdForSingleShape.GECmdAddShape;
import commands.cmdForPanel.GECmdSetPanelFillColor;
import commands.cmdForPanel.GECmdSetPanelLineColor;
import commands.cmdForMultipleShapes.*;
import constants.GEConstants;
import shapes.*;
import transformer.GEDrawer;
import transformer.GEMover;
import transformer.GEResizer;
import transformer.GETransformer;
import utils.GECursorManager;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

public class GEDrawingPanel extends JPanel {
    private GEShape currentShape;
    private ArrayList<GEShape> shapeList;
    private ArrayList<GEShape> selectedShapes; // added
    private GEConstants.EState currentState;
    private GETransformer transformer;
    private MouseDrawingHandler drawingHandler;
    private Color fillColor;
    private Color lineColor;
    private GECursorManager cursorManager;
    private Stack<GECommand> redoStack; // added
    private Stack<GECommand> undoStack; // added

    public GEDrawingPanel() {
        super();

        shapeList = new ArrayList<GEShape>();
        selectedShapes = new ArrayList<GEShape>(); // added
        currentState = GEConstants.EState.Idle;
        drawingHandler = new MouseDrawingHandler();

        lineColor = GEConstants.DEFAULT_LINE_COLOR;
        fillColor = GEConstants.DEFAULT_FILL_COLOR;
        cursorManager = new GECursorManager();

        redoStack = new Stack<GECommand>(); // added
        undoStack = new Stack<GECommand>(); // added

        this.addMouseListener(drawingHandler);
        this.addMouseMotionListener(drawingHandler);
        this.setBackground(GEConstants.BACKGROUND_COLOR);
        this.setForeground(GEConstants.FOREGROUND_COLOR);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;

        for (GEShape shape : shapeList) shape.draw(g2D);
    }

    public boolean isAnyShapeSelected() { // added
        return selectedShapes != null && selectedShapes.size() > 0;
    }

    public boolean isUndoable() { // added
        return undoStack != null && undoStack.size() > 0;
    }

    public boolean isRedoable() { // added
        return redoStack != null && redoStack.size() > 0;
    }

    public boolean isPasteable() { // added
        return isUndoable() && (undoStack.peek() instanceof IGEPasteable);
    }

    public boolean isIdle() { // added
        return currentState == GEConstants.EState.Idle;
    }

    public boolean isGroupable() { // added
        return selectedShapes != null && selectedShapes.size() > 1;
    }

    public boolean isUngroupable() { // added
        if (isAnyShapeSelected()) {
            for (GEShape shape : selectedShapes) {
                if (shape instanceof GEGroupedShape) return true;
            }
        }

        return false;
    }

    public String getUndoName() { // added
        return undoStack.peek().toString();
    }

    public String getRedoName() { // added
        return redoStack.peek().toString();
    }

    public Color getLineColor() { // added
        return lineColor;
    }

    public Color getFillColor() { // added
        return fillColor;
    }

    public void setPanelLineColor(Color lineColor) { // added
        if (lineColor != null) this.lineColor = lineColor;
    }

    public void setPanelFillColor(Color fillColor) { // added
        if (fillColor != null) this.fillColor = fillColor;
    }

    public void setLineColor(Color lineColor) { // changed
        if (lineColor != null) {
            if (isAnyShapeSelected()) performCmd(new GECmdSetShapesLineColor(selectedShapes, lineColor));
            else performCmd(new GECmdSetPanelLineColor(this, lineColor));
        }
    }

    public void setFillColor(Color fillColor) { // changed
        if (fillColor != null) {
            if (isAnyShapeSelected()) performCmd(new GECmdSetShapesFillColor(selectedShapes, fillColor));
            else performCmd(new GECmdSetPanelFillColor(this, fillColor));
        }
    }

    public void setCurrentShape(GEShape currentShape) {
        this.currentShape = currentShape;
    }

    private void initDraw() {
        currentShape = currentShape.clone();
        currentShape.setLineColor(lineColor);
        currentShape.setFillColor(fillColor);
    }

    private void continueDrawing(Point currentP) {
        ((GEDrawer)transformer).continueDrawing(currentP);
    }

    private void finishDraw() { // changed
        if (currentShape != null && currentShape.isWorthAdding()) performCmd(new GECmdAddShape(shapeList, currentShape));
    }

    public void undo() { // added
        if (isUndoable()) {
            clearSelectedShapes();
            GECommand toUndo = undoStack.pop();
            toUndo.undo();
            redoStack.add(toUndo);
            showSelectedShapes();
        }
    }

    public void redo() { // added
        if (isRedoable()) {
            clearSelectedShapes();
            GECommand toRedo = redoStack.pop();
            toRedo.execute();
            undoStack.add(toRedo);
            showSelectedShapes();
        }
    }

    public void removeSelectedShape() { // added
        if (isAnyShapeSelected()) performCmd(new GECmdRemoveShapes(shapeList, selectedShapes));
    }

    public void cutSelectedShape() { // added
        if (isAnyShapeSelected()) performCmd(new GECmdCutShapes(shapeList, selectedShapes));
    }

    public void copySelectedShape() { // added
        if (isAnyShapeSelected()) performCmd(new GECmdCopyShapes(selectedShapes));
    }

    public void pasteSelectedShape(Point pastePoint) { // added
        if (isPasteable()) {
            ArrayList<GEShape> shapesToPaste = ((IGEPasteable)undoStack.peek()).getShapesToPaste();

            if (shapesToPaste != null && shapesToPaste.size() > 0)
                performCmd(new GECmdPasteShapes(shapeList, shapesToPaste, pastePoint));
        }
    }

    public void groupSelectedShape() { // added
        if (isGroupable()) performCmd(new GECmdGroupShapes(shapeList, selectedShapes));
    }

    public void ungroupSelectedShape() { // added
        if (isUngroupable()) performCmd(new GECmdUngroupShapes(shapeList, selectedShapes));
    }

    public void changeSelectedShapeStroke(Object newStroke) { // added
        if (isAnyShapeSelected()) {
            if (newStroke instanceof BasicStroke) performCmd(new GECmdChangeShapesStroke(selectedShapes, (BasicStroke)newStroke));
            else if (newStroke instanceof Integer) performCmd(new GECmdChangeShapesStroke(selectedShapes, (int)newStroke));
        }
    }

    public void moveSelectedShape(ArrayList<GEShape> movedShapes) { // added
        if (isAnyShapeSelected()) performCmd(new GECmdMoveShapes(shapeList, selectedShapes, movedShapes));
    }

    public void resizeSelecteShape(ArrayList<GEShape> resizedShapes) { // added
        if (isAnyShapeSelected()) performCmd(new GECmdResizeShapes(shapeList, selectedShapes, resizedShapes));
    }

    private void performCmd(GECommand command) { // added
        if (!(command instanceof GECmdPasteShapes) && isPasteable()) undo();

        clearSelectedShapes();
        command.execute();
        undoStack.add(command);

        if (isRedoable()) redoStack.clear();

        showSelectedShapes();
    }

    private GEShape onShape(Point p) {
        for (int i = shapeList.size() - 1; i >= 0; i--) {
            GEShape shape = shapeList.get(i);

            if (shape.onShape(p)) return shape;
        }

        return null;
    }

    private void clearSelectedShapes() {
        for (GEShape shape : shapeList) shape.setSelected(false);

        selectedShapes.clear(); // added
    }

    private void showSelectedShapes() { // added
        selectedShapes.clear();

        for (GEShape shape : shapeList) {
            if (shape.isSelected()) selectedShapes.add(shape);
        }

        repaint();
        getRootPane().getParent().repaint();
    }

    private void changeCurrentState(GEConstants.EState newState) { // added
        currentState = newState;
        getRootPane().getParent().repaint();
    }

    private class MouseDrawingHandler extends MouseInputAdapter {
        @Override
        public void mouseDragged(MouseEvent e) {
            if (currentState != GEConstants.EState.Idle)
                transformer.transformer((Graphics2D)getGraphics(), e.getPoint());
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (currentState == GEConstants.EState.Idle) {
                if (currentShape != null) { // changed
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        clearSelectedShapes();
                        initDraw();
                        transformer = new GEDrawer(currentShape);
                        transformer.init(e.getPoint());

                        if (currentShape instanceof GEPolygon)
                            changeCurrentState(GEConstants.EState.NPointDrawing);
                        else
                            changeCurrentState(GEConstants.EState.TwoPointsDrawing);
                    }
                } else {
                    GEShape selectedShape = onShape(e.getPoint());

                    if (selectedShape != null) { // changed
                        if ((e.getModifiers() & KeyEvent.CTRL_MASK) == KeyEvent.CTRL_MASK) {
                            if (selectedShapes.contains(selectedShape)) selectedShape.setSelected(false);
                            else selectedShape.setSelected(true);
                        } else if (selectedShapes.contains(selectedShape)) {
                            if (selectedShape.onAnchor(e.getPoint()) == GEConstants.EAnchorTypes.NONE)
                                changeCurrentState(GEConstants.EState.Moving);
                            else
                                changeCurrentState(GEConstants.EState.Resizing);
                        } else {
                            clearSelectedShapes();
                            selectedShape.setSelected(true);
                        }

                        showSelectedShapes();

                        if (currentState == GEConstants.EState.Moving) {
                            transformer = new GEMover(selectedShapes);
                            transformer.init(e.getPoint());
                        } else if (currentState == GEConstants.EState.Resizing) {
                            transformer = new GEResizer(selectedShape, selectedShapes);
                            transformer.init(e.getPoint());
                        }
                    } else clearSelectedShapes();
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (currentState == GEConstants.EState.TwoPointsDrawing) finishDraw();
            else if (currentState == GEConstants.EState.NPointDrawing) return;
            else if (currentState == GEConstants.EState.Moving)
                moveSelectedShape(((GEMover)transformer).finalize(e.getPoint()));
            else if (currentState == GEConstants.EState.Resizing) {
                resizeSelecteShape(((GEResizer)transformer).finalize(e.getPoint()));
            }

            changeCurrentState(GEConstants.EState.Idle);
            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (currentState == GEConstants.EState.NPointDrawing) {
                    if (e.getClickCount() == 1)
                        continueDrawing(e.getPoint());
                    else if (e.getClickCount() == 2) {
                        finishDraw();
                        changeCurrentState(GEConstants.EState.Idle);
                        repaint();
                    }
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) pasteSelectedShape(e.getPoint());
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (currentState == GEConstants.EState.NPointDrawing)
                transformer.transformer((Graphics2D)getGraphics(), e.getPoint());
            else if (currentState == GEConstants.EState.Idle) {
                GEShape shape = onShape(e.getPoint());

                if (shape == null) setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                else if (shape.isSelected()) {
                    GEConstants.EAnchorTypes anchorType = shape.onAnchor(e.getPoint());
                    setCursor(cursorManager.get(anchorType.ordinal()));
                }
            }
        }
    }
}
