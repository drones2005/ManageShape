package com.technoserv.mogs.figures;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figure {
    private final int type = 0;

    public Ellipse(int x1, int y1, int x2, int y2, Color fillColor, Color borderColor, int number) {
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
        shape = new Ellipse2D.Float(Math.min(points[0].x,points[1].x), Math.min(points[0].y,points[1].y), Math.abs(points[0].x-points[1].x), Math.abs(points[0].y-points[1].y));
    }

    @Override
    public boolean pointInsideObj(int x, int y) {
        return Math.pow(x-Math.abs(points[0].x+points[1].x)/2,2)/Math.pow((points[0].x-points[1].x)/2,2) + Math.pow(y-Math.abs(points[0].y+points[1].y)/2,2)/Math.pow((points[0].y-points[1].y)/2,2) <= 1;
    }

    @Override
    public void setExtremePoint(int x, int y){
        points[1].x = x;
        points[1].y = y;
    }

    @Override
    public int getCountClick() {
        return 0;
    }

    @Override
    public String getNumberObj() {
        return "Ellips_" + String.valueOf(this.number);
    }

    public int getType() {
        return type;
    }
}
