package com.technoserv.mogs.panels;

import com.technoserv.mogs.settings.CurrentSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MogsInfoBar extends JToolBar {
    private CurrentSettings cs;
    private MogsMap map;
    private Logger log;
    private JLabel cursorCoordLabel;
    private JLabel countObjectLabel;

    public MogsInfoBar(CurrentSettings cs, final MogsMap map) {
        this.log = LogManager.getLogger(MogsInfoBar.class);
        this.cs = cs;
        this.map = map;


        log.info("Создание панели информации: начало");
        log.info("Координаты: (" + cs.getInfobar_offset_x() + "," + cs.getInfobar_offset_y() + "); w = " + cs.getInfobar_width() + ", h = " + cs.getInfobar_height());
        try {
            setBounds(cs.getInfobar_offset_x(), cs.getInfobar_offset_y(), cs.getInfobar_width(), cs.getInfobar_height());
            setName("infobar");
            setBorder(BorderFactory.createEtchedBorder());
            setOrientation(JToolBar.HORIZONTAL);
            cursorCoordLabel = addLabelInfo(5, 10, 80, 10);
            countObjectLabel = addLabelInfo(100, 10, 150, 10);

            map.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    super.mouseMoved(e);
                    cursorCoordLabel.setText(String.valueOf(e.getX()) + " : " + String.valueOf(e.getY()) + " пкс");
                    countObjectLabel.setText("Количество объектов: " + String.valueOf(map.getCountObj()));
                }
            });

            map.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    countObjectLabel.setText("Количество объектов: " + String.valueOf(map.getCountObj()));
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    countObjectLabel.setText("Количество объектов: " + String.valueOf(map.getCountObj()));
                }
            });

            setLayout(null);

        } catch(Exception ex){
            log.info("------------------------------------------------------");
            log.info("Ошибка при создании панели информации " + ex.getClass());
            log.info("------------------------------------------------------");
            log.info("Создание панели информации: конец");
        }
        log.info("Создание панели информации: конец");
    }

    public JLabel addLabelInfo(int x, int y, int width, int height){
        JLabel label = new JLabel("");
        label.setBounds(x, y, width, height);
        add(label);
        return label;
    }
}
