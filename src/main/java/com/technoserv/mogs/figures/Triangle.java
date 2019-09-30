package com.technoserv.mogs.figures;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Triangle extends Figure {
    private final int type = 3;


    private int countClick;

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3, Color fillColor, Color borderColor, int number ) {
        points = new Point[3];
        points[0] = new Point(x1,y1);
        points[1] = new Point(x2,y2);
        points[2] = new Point(x3,y3);
        setFillColor(fillColor);
        setBorderColor(borderColor);
        setFillColorMouseOver(borderColor);
        setBorderColorMouseOver(fillColor);
        setCountClick();
        makeGraph();
        setNumber(number);
    }

    @Override
    public boolean pointInsideObj(int x, int y) {
        float alpha = (float)((points[1].y - points[2].y)*(x - points[2].x) + (points[2].x - points[1].x)*(y - points[2].y)) /
                (float)((points[1].y - points[2].y)*(points[0].x - points[2].x) + (points[2].x - points[1].x)*(points[0].y - points[2].y));
        float beta = (float)((points[2].y - points[0].y)*(x - points[2].x) + (points[0].x - points[2].x)*(y - points[2].y)) /
                (float)((points[1].y - points[2].y)*(points[0].x - points[2].x) + (points[2].x - points[1].x)*(points[0].y - points[2].y));
        float gamma = 1.0f - alpha - beta;

        return alpha > 0 && beta > 0 && gamma > 0;
    }

    @Override
    public void setExtremePoint(int x, int y) {
        setCountClick();
        if(getCountClick() == 2){
            points[1].x = x;
            points[1].y = y;
        } else if(getCountClick() == 3) {
            points[2].x = x;
            points[2].y = y;
        }
    }

    @Override
    public String getNumberObj() {
        return "Triangle_" + String.valueOf(this.number);
    }

    // создание объекта фигуры
    public void makeGraph(){
        Polygon graph = new Polygon();
        graph.addPoint(points[0].x, points[0].y);
        graph.addPoint(points[1].x, points[1].y);
        graph.addPoint(points[2].x, points[2].y);
        shape = graph;
    }

    public int getType() {
        return type;
    }

    public int getCountClick() {
        return countClick;
    }

    public void setCountClick() {
        this.countClick++;
    }
}
