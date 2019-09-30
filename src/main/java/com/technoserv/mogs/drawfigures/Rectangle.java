package com.technoserv.mogs.drawfigures;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Rectangle extends JComponent{
    private List<Rectangle2D> rectangles = new ArrayList<Rectangle2D>();
    private Point start;
    private Point end;

    public Rectangle() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                start = e.getPoint();
                end = start;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Rectangle2D r = makeRectangle(start.x,start.y,e.getX(),e.getY());
                rectangles.add(r);
                start = null;
                end = null;
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
               end = new Point(e.getX(),e.getY());
               repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        for(Rectangle2D rectangle : rectangles){
            g2.draw(rectangle);
        }

        if(start != null && end != null){
            Rectangle2D rectangle = makeRectangle(start.x,start.y,end.x,end.y);
            g2.draw(rectangle);
        }
    }

    public Rectangle2D.Double makeRectangle(int x1, int y1, int x2, int y2){
        return new Rectangle2D.Double(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1-x2), Math.abs(y1-y2));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Примоугольник");
        frame.setSize(500,500);
        frame.add(new Rectangle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
