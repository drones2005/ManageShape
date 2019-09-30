package com.technoserv.mogs.drawfigures;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.List;
import java.util.ArrayList;

public class Triangle extends JComponent{

    private List<Polygon> triangles = new ArrayList<Polygon>();
    private int[] triangleX = new int[3];
    private int[] triangleY = new int[3];
    private Point point1;
    private Point point2;
    private Point point3;

    public Triangle(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if(point3 != null){
                    triangles.add(new Polygon(triangleX,triangleY,3));
                    point1 = null; point2 = null; point3 = null;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(point1 == null){
                    point1 = new Point(e.getX(), e.getY());
                    triangleX[0] = point1.x;
                    triangleY[0] = point1.y;
                }else{
                    if(point2 == null){
                        point2 = new Point(e.getX(),e.getY());
                        triangleX[1] = point2.x;
                        triangleY[1] = point2.y;
                    }else{
                        if(point3 == null){
                            point3 = new Point(e.getX(),e.getY());
                            triangleX[2] = point3.x;
                            triangleY[2] = point3.y;
                        }
                    }
                }
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        for(Polygon triangle : triangles){
            g2.drawPolygon(triangle);
        }

        if(point1 != null)
        if (point2 != null) {
            Line2D line1 = makeLine(point1.x, point1.y, point2.x, point2.y);
            g2.draw(line1);
        }
        if (point3 != null) {
            Line2D line1 = makeLine(point1.x, point1.y, point2.x, point2.y);
            Line2D line2 = makeLine(point2.x, point2.y, point3.x, point3.y);
            Line2D line3 = makeLine(point3.x, point3.y, point1.x, point1.y);
            g2.draw(line1);
            g2.draw(line2);
            g2.draw(line3);
        }
    }

    public Line2D.Double makeLine(int x1, int y1, int x2, int y2){
        return new Line2D.Double(x1,y1,x2,y2);
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Треугольник");
        frame.add(new Triangle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        frame.setSize(500,500);
        frame.setVisible(true);
    }
}
