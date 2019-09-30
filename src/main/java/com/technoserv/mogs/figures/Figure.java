package com.technoserv.mogs.figures;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public abstract class Figure extends JComponent implements Serializable {
    protected int number; // номер фигуры
    protected boolean pickObj; // признак выделенного объекта
    private Color fillColor; // цвет заливки фигуры
    private Color borderColor; // цвет границы фигуры
    private Color borderColorMouseOver; // цвет границы выделенной фигуры
    private Color fillColorMouseOver; // цвет заливки выделенной фигуры
    protected Shape shape;
    protected Point[] points; // координаты

    public abstract boolean pointInsideObj(int x, int y); // проверка принадлежности точки фигуре
    public abstract void setExtremePoint(int x, int y); // фиксация фигуры
    public abstract int getCountClick(); // количество кликов для построения полигона; для эллипса и прямоугольника не используется
    public abstract String getNumberObj(); // получить номер фигуры
    //public abstract void moveObj(int delta_x, int delta_y); // передвинуть объект
    public abstract void makeGraph(); // создание объекта фигуры

    // прорисовка фигуры
    public void draw(Graphics2D g2){
        g2.setColor(getFillColor());
        g2.fill(shape);
        g2.setColor(getBorderColor());

        if (!isPickObj()){
            g2.setStroke(new BasicStroke(1.0f));
            g2.setColor(getBorderColor());
        } else {
            g2.setStroke(new BasicStroke(5.0f));
            g2.setColor(Color.PINK);
        }
        g2.draw(shape);
    }
    // передвинуть объект
    public void moveObj(int delta_x, int delta_y) {
        for(int i = 0; i < points.length; i++){
            points[i].x = points[i].x + delta_x;
            points[i].y = points[i].y + delta_y;
        }
        makeGraph();
    }

    public int getNumber() {return number;}

    public void setNumber(int number) {this.number = number;}

    public boolean isPickObj() {
        return pickObj;
    }

    public void setPickObj(boolean pickObj) {
        this.pickObj = pickObj;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getBorderColorMouseOver() {
        return borderColorMouseOver;
    }

    public void setBorderColorMouseOver(Color borderColorMouseOver) {
        this.borderColorMouseOver = borderColorMouseOver;
    }

    public Color getFillColorMouseOver() {
        return fillColorMouseOver;
    }

    public void setFillColorMouseOver(Color fillColorMouseOver) {
        this.fillColorMouseOver = fillColorMouseOver;
    }
}

