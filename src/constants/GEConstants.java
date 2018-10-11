package constants;

import java.awt.*;

public class GEConstants {
    public static final int WIDTH_MAINFRAME = 400;
    public static final int HEIGHT_MAINFRAME = 600;
    public static final String TITLE_MAINFRAME = "GraphicEditor-12";

    public static final String TITLE_FILEMENU = "파일";
    public static final String TITLE_EDITMENU = "편집";
    public static final String TITLE_COLORMENU = "컬러";

    public static enum EFileMenuItems { 새로만들기, 열기, 저장, 다른이름으로저장, 종료 }
    public static enum EEditMenuItems { Undo, Redo, 삭제, 잘라내기, 복사, 붙이기, Group, Ungroup, 선속성변경 } // changed
    public static enum EColorMenuItems { SetLineColor, ClearLineColor, SetFillColor, ClearFillColor }

    public static final String TITLE_TOOLBAR = "Shape Tools";
    public static enum EToolBarButtons { Select, Rectangle, Line, Ellipse, Polygon }
    public static final String IMG_URL = "images/";
    public static final String TOOLBAR_BTN = ".gif";
    public static final String TOOLBAR_BTN_SELECT = "SLT.gif";

    public static final Color FOREGROUND_COLOR = Color.BLACK;
    public static final Color BACKGROUND_COLOR = Color.WHITE;
    public static enum EState { Idle, TwoPointsDrawing, NPointDrawing, Moving, Resizing }
    public static final Color DEFAULT_LINE_COLOR = Color.BLACK;
    public static final Color DEFAULT_FILL_COLOR = Color.WHITE;

    public static final int ANCHOR_W = 6;
    public static final int ANCHOR_H = 6;
    public static final int RR_OFFSET = 30;
    public static final Color ANCHOR_LINE_COLOR = Color.BLACK;
    public static final Color ANCHOR_FILL_COLOR = Color.WHITE;
    public static enum EAnchorTypes { NW, NN, NE, WW, EE, SW, SS, SE, RR, NONE }

    public static final String TITLE_FILLCOLOR = "Select Fill Color";
    public static final String TITLE_LINECOLOR = "Select Line Color";

    public static final int DEFAULT_DASH_OFFSET = 4;
    public static final int DEFAULT_DASHEDLINE_WIDTH = 1;
}
