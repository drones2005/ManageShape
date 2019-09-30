package com.technoserv.mogs.panels;

import com.technoserv.mogs.settings.CurrentSettings;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MogsColorBar extends JToolBar {
    private JButton colorfillbutton;
    private JButton colorborderbutton;
    private CurrentSettings cs;
    private int activeColor;
    private Logger log;

    public MogsColorBar(CurrentSettings cs, Logger log) {
        this.cs = cs;
        this.log = log;

        log.info("Создание панели цветов: начало");
        log.info("Координаты: (" + cs.getColorbar_offset_x() + "," + cs.getColorbar_offset_y() + "); w = " + cs.getColorbar_width() + ", h = " + cs.getColorbar_height());
        try {
            setOrientation(JToolBar.HORIZONTAL);
            setName("colorbar");
            setBounds(cs.getColorbar_offset_x(), cs.getColorbar_offset_y(), cs.getColorbar_width(), cs.getColorbar_height());
            setBorder(BorderFactory.createEtchedBorder());
            setActiveColor(1);
            addButtonStatusColor(cs.getColorbar_fillcolor(), cs.getColorbar_bordercolor());
            addButtonColor(Color.RED, 90);
            addButtonColor(Color.ORANGE, 110);
            addButtonColor(Color.YELLOW, 130);
            addButtonColor(Color.GREEN, 150);
            addButtonColor(Color.BLUE, 170);
            addButtonColor(Color.CYAN, 190);
            addButtonColor(Color.MAGENTA, 210);
            addButtonColor(Color.BLACK, 230);
            setLayout(null);
        } catch(Exception ex){
            log.info("------------------------------------------------------");
            log.info("Ошибка при создании панели цветов " + ex.getClass());
            log.info("------------------------------------------------------");
            log.info("Создание панели цветов: конец");
        }
        log.info("Создание панели цветов: конец");
    }

    public void addButtonColor(Color color, int x){
        final JButton button = new JButton();
        button.setBackground(color);
        button.setBounds(x,15, 20,20);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getActiveColor() == 1) {
                    colorfillbutton.setBackground(button.getBackground());
                    cs.setColorbar_fillcolor(colorfillbutton.getBackground());
                } else {
                    colorborderbutton.setBackground(button.getBackground());
                    cs.setColorbar_bordercolor(colorborderbutton.getBackground());
                }
            }
        });
        add(button);
    }

    public void addButtonStatusColor(Color fillColor, Color borderColor){
        colorfillbutton = new JButton();
        colorfillbutton.setBackground(fillColor);
        colorfillbutton.setBounds(15,10,30,30);
        colorfillbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setActiveColor(1);
            }
        });
        add(colorfillbutton);

        colorborderbutton = new JButton();
        colorborderbutton.setBackground(borderColor);
        colorborderbutton.setBounds(50,10, 30, 30);
        colorborderbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setActiveColor(2);
            }
        });

        add(colorborderbutton);
    }

    public JButton getFillColorbutton() {
        return colorfillbutton;
    }

    public JButton getColorborderbutton() {
        return colorborderbutton;
    }

    public int getActiveColor() {
        return activeColor;
    }

    public void setActiveColor(int activeColor) {
        this.activeColor = activeColor;
    }
}
