package com.technoserv.mogs.figures;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Figure {
    private final int type = 4;
    private Point[] points;

    public Rectangle(int x1, int y1, int x2, int y2, Color fillColor, Color borderColor, int number) {
        points = new Point[2];
        points[0] = new Point(x1,y1);
        points[1] = new Point(x2,y2);
        setFillColor(fillColor);
        setBorderColor(borderColor);
        setFillColorMouseOver(borderColor);
        setBorderColorMouseOver(fillColor);
        makeGraph();
        setNumber(number);
    }

    @Override
    public void makeGraph(){
        shape = new Rectangle2D.Double(Math.min(points[0].x,points[1].x), Math.min(points[0].y,points[1].y), Math.abs(points[0].x-points[1].x), Math.abs(points[0].y-points[1].y));
    }

    @Override
    public boolean pointInsideObj(int x, int y) {
        return Math.max(points[0].x,points[1].x) >= x && Math.min(points[0].x, points[1].x) <= x && Math.max(points[0].y, points[1].y) >= y && Math.min(points[0].y,points[1].y) <= y;
    }

    @Override
    public void setExtremePoint(int x, int y) {
        points[1].x = x;
        points[1].y = y;
    }

    @Override
    public int getCountClick() {
        return 0;
    }

    @Override
    public String getNumberObj() {
        return "Rectangle_" + String.valueOf(this.number);
    }

    @Override
    public void moveObj(int delta_x, int delta_y) {
        for(int i = 0; i < points.length; i++){
            points[i].x = points[i].x + delta_x;
            points[i].y = points[i].y + delta_y;
        }
        makeGraph();
    }

    public int getType() {
        return type;
    }
}
