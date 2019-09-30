package com.technoserv.mogs.draw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class DrawingCircle extends JComponent {

    private List<Ellipse2D> ellipses = new ArrayList<Ellipse2D>();

    private Point start;
    private Point end;

    public DrawingCircle() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                start = new Point(e.getX(), e.getY());
                end = start;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Ellipse2D r = makeEllipse(start.x, start.y, e.getX(), e.getY());
                ellipses.add(r);
                start = null;
                end = null;
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                end = new Point(e.getX(), e.getY());
                repaint();
            }
        });
    }

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

    private Ellipse2D.Double makeEllipse(int x1, int y1, int x2, int y2) {
        return new Ellipse2D.Double(Math.min(x1, x2), Math.min(y1, y2),
                Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(600, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new DrawingCircle());
        f.setVisible(true);
    }
}
