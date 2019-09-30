package com.technoserv.mogs.panels;

import com.technoserv.mogs.figures.Ellipse;
import com.technoserv.mogs.figures.Rectangle;
import com.technoserv.mogs.figures.Triangle;
import com.technoserv.mogs.figures.Figure;
import com.technoserv.mogs.settings.CurrentSettings;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;


public class MogsMap extends JPanel{
    // размер панели рисования ----------------------------
    private int x;
    private int y;
    private int width;
    private int height;
    // текущие координаты мыши на карте -------------------
    private int mx;
    private int my;
    // текущие координаты зажатия левой клавиши мыши в режиме finger
    private int fixed_x;
    private int fixed_y;

    private int countObj; // количество объектов
    private CurrentSettings cs; // объект настроек

    private List<Figure> figures; //список объектов
    private Figure figure;
    private Logger log;

    public MogsMap(final CurrentSettings cs, final Logger log) {
        this.log = log;
        this.cs = cs;
        this.x = cs.getMap_offset_x();
        this.y = cs.getMap_offset_y();
        this.width = cs.getMap_width();
        this.height = cs.getMap_height();
        countObj = 0;
        figures = new ArrayList<Figure>();

        log.info("Создание панели рисования: начало");
        log.info("Координаты: (" + cs.getMap_offset_x() + "," + cs.getMap_offset_y() + "); w = " + cs.getMap_width() + ", h = " + cs.getMap_height());
        try {
            setLayout(null);
            setBackground(cs.getMap_background());
            setBounds(x, y, width, height);
            setBorder(BorderFactory.createEtchedBorder());

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);

                    // Создание объекта при нажатии лековой клавиши мыши в поле карты
                    if (cs.getToolbar_mode() == cs.getFigures().get("ellipse")) {
                        figure = new Ellipse(e.getX(), e.getY(), e.getX(), e.getY(), cs.getColorbar_fillcolor(), cs.getColorbar_bordercolor(), countObj);
                    }
                    if (cs.getToolbar_mode() == cs.getFigures().get("rectangle")) {
                        figure = new Rectangle(e.getX(), e.getY(), e.getX(), e.getY(), cs.getColorbar_fillcolor(), cs.getColorbar_bordercolor(), countObj);
                    }
                    if (cs.getToolbar_mode() == cs.getFigures().get("triangle")) {
                        if (figure == null) {
                            figure = new Triangle(e.getX(), e.getY(), e.getX(), e.getY(), e.getX(), e.getY(), cs.getColorbar_fillcolor(), cs.getColorbar_bordercolor(), countObj);
                        } else {
                            figure.setExtremePoint(e.getX(), e.getY());
                        }
                    }
                    if (cs.getToolbar_mode() == cs.getFigures().get("finger")) {
                        fixed_x = e.getX();
                        fixed_y = e.getY();
                    }
                    repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    mx = e.getX();
                    my = e.getY();
                    // Фиксация объекта при отпускании левой клавиши мыши в поле карты
                    if (cs.getToolbar_mode() == cs.getFigures().get("ellipse")) {
                        figure.setExtremePoint(e.getX(), e.getY());
                        figure.makeGraph();
                        figures.add(figure);
                        countObj = figures.size();
                        log.info("Объект " + figure.getNumberObj() + " добавлен на карту");
                        figure = null;
                    }
                    if (cs.getToolbar_mode() == cs.getFigures().get("rectangle")) {
                        figure.setExtremePoint(e.getX(), e.getY());
                        figure.makeGraph();
                        figures.add(figure);
                        countObj = figures.size();
                        log.info("Объект " + figure.getNumberObj() + " добавлен на карту");
                        figure = null;
                    }
                    if (cs.getToolbar_mode() == cs.getFigures().get("triangle")) {
                        if (figure.getCountClick() == 2) {
                            figure.makeGraph();
                        } else if (figure.getCountClick() == 3) {
                            figure.makeGraph();
                            figures.add(figure);
                            countObj = figures.size();
                            log.info("Объект " + figure.getNumberObj() + " добавлен на карту");
                            figure = null;
                        }
                    }
                    repaint();
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

                    // Удаление объекта двойным кликом левой клавиши мыши
                    if (cs.getToolbar_mode() == cs.getFigures().get("finger")) {
                        if (e.getClickCount() == 2) {
                            for (int i = figures.size() - 1; i >= 0; i--) {
                                if (figures.get(i).isPickObj()) {
                                    log.info("Объект " + figures.get(i).getNumberObj() + " удален с карты");
                                    figures.remove(i);
                                    countObj = figures.size();
                                    break;
                                }
                            }
                        }
                    }

                    repaint();
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    super.mouseDragged(e);
                    mx = e.getX();
                    my = e.getY();
                    // корректировка размеров объекта после нажатия левой клавиши мыши в поле карты
                    if (cs.getToolbar_mode() == cs.getFigures().get("ellipse")) {
                        figure.setExtremePoint(e.getX(), e.getY());
                        figure.makeGraph();
                    }
                    if (cs.getToolbar_mode() == cs.getFigures().get("rectangle")) {
                        figure.setExtremePoint(e.getX(), e.getY());
                        figure.makeGraph();
                    }
                    //перемещение объекта
                    if (cs.getToolbar_mode() == cs.getFigures().get("finger")) {

                        for (int i = figures.size() - 1; i >= 0; i--) {
                            if (figures.get(i).isPickObj()) {
                                figures.get(i).moveObj(e.getX() - fixed_x, e.getY() - fixed_y);
                                fixed_x = e.getX();
                                fixed_y = e.getY();
                                break;
                            }
                        }
                    }
                    repaint();
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    super.mouseMoved(e);
                    mx = e.getX();
                    my = e.getY();
                    if (cs.getToolbar_mode() == cs.getFigures().get("finger")) {
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                        // cнимаем со всех фигур выделение
                        for (Figure figure : figures) {
                            figure.setPickObj(false);
                            //log.info("Фигура " + figure.getNumberObj() + " - " + String.valueOf(figure.isPickObj()));
                        }
                        // находим самый верхний объект (number = max), на который наведен курсор

                        for(int i = figures.size() - 1; i >= 0; i--){
                            if (figures.get(i).pointInsideObj(mx, my)) {
                                figures.get(i).setPickObj(figures.get(i).pointInsideObj(mx, my));
                                break;
                            }
                        }
                    } else {
                        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                    }
                    repaint();
                }
            });
        } catch(Exception ex){
            log.info("------------------------------------------------------");
            log.info("Ошибка при создании панели рисования " + ex.getClass());
            log.info("------------------------------------------------------");
            log.info("Создание панели рисования: конец");
        }
        log.info("Создание панели рисования: конец");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        for (Figure figure : figures) {
            figure.draw(g2);
        }

        if(figure != null) {
            figure.draw(g2);
        }
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public void setFigure(List<Figure> figures) {
        this.figures = figures;
        this.countObj = figures.size();
    }

    public int getMx() {
        return mx;
    }

    public int getMy() {
        return my;
    }

    public int getCountObj() {
        return countObj;
    }
}
