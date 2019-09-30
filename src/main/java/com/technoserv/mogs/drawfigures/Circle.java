package com.technoserv.mogs.drawfigures;

import com.technoserv.mogs.panels.MogsMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class Circle extends JComponent{

    private List<Ellipse2D> ellipses = new ArrayList<Ellipse2D>();
    private Point start;
    private Point end;

    public Circle(MogsMap map) {
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                start = e.getPoint();
                end = start;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Ellipse2D r = makeEllipse(start.x, start.y, e.getX(), e.getY());
                ellipses.add(r);
                start = null;
                end = null;
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                end = new Point(e.getX(),e.getY());
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (Ellipse2D el : ellipses) {
            g2.draw(el);
        }

        if (start != null && end != null) {
            Ellipse2D el = makeEllipse(start.x, start.y, end.x, end.y);
            g2.draw(el);
        }
    }

    public Ellipse2D.Double makeEllipse(int x1, int y1, int x2, int y2){
        return new Ellipse2D.Double(Math.min(x1,x2),Math.min(y1,y2), Math.abs(x1-x2), Math.abs(y1-y2));
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Эллипс");
        frame.setSize(500,500);
        frame.setBackground(Color.RED);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
