package com.technoserv.mogs.panels;

import com.technoserv.mogs.settings.CurrentSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;


public class MogsToolBar extends JToolBar {
    private CurrentSettings cs;
    private MogsMap map;
    private Logger log;

    public MogsToolBar(final CurrentSettings cs, MogsMap map) {
        this.cs = cs;
        this.map = map;
        this.log = LogManager.getLogger(MogsToolBar.class);

        log.info("Создание панели инструментов: начало");
        log.info("Координаты: (" + cs.getToolbar_offset_x() + "," + cs.getToolbar_offset_y() + "); w = " + cs.getToolbar_width() + ", h = " + cs.getToolbar_height());
        try {
            setOrientation(JToolBar.VERTICAL);
            setBounds(cs.getToolbar_offset_x(), cs.getToolbar_offset_y(), cs.getToolbar_width(), cs.getToolbar_height());
            setName("toolbar");
            setBorder(BorderFactory.createEtchedBorder());

            for (Map.Entry<String, Integer> figure : cs.getFigures().entrySet()) {
                addButtonMode(figure.getKey(), figure.getValue());
            }

        } catch(Exception ex){
            log.info("------------------------------------------------------");
            log.info("Ошибка при создание панели инструментов " + ex.getClass());
            log.info("------------------------------------------------------");
            log.info("Создание панели инструментов: конец");
        }
        log.info("Создание панели инструментов: конец");
    }

    public void addButtonMode(String name, final int mode){
        final JButton button = new JButton(new ImageIcon("./" +cs.getPathpics() + name + ".png"));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.setToolbar_mode(mode);
                button.getModel().setPressed(true);
            }
        });
        add(button);
    }

    public void addButtonClean(String name){
        JButton button = new JButton(new ImageIcon("./" + cs.getPathpics() + name + ".png"));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.getFigures().clear();
                map.repaint();
                log.info("Удаление всех объектов");
            }
        });
        add(button);
    }
}
