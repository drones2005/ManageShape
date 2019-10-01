package com.technoserv.mogs;

import com.technoserv.mogs.panels.*;
import com.technoserv.mogs.settings.CurrentSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.IOException;

public class ManageShapeObject {
    private static CurrentSettings cs;

    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
        //Logger log = LogManager.getLogger("log4j2.xml");
        Logger log = LogManager.getLogger(ManageShapeObject.class);

        CurrentSettings cs = new CurrentSettings("config.properties", log);

        log.info("Вход");

        JFrame frame = new JFrame("Управление объектами фигур");
        frame.setSize(cs.getWindow_width(), cs.getWindow_height());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MogsMap map = new MogsMap(cs);
        MogsMenuBar menuBar = new MogsMenuBar(cs, map);
        MogsColorBar colorBar = new MogsColorBar(cs);
        MogsToolBar toolBar = new MogsToolBar(cs, map);
        MogsInfoBar infoBar = new MogsInfoBar(cs, map);

        frame.setJMenuBar(menuBar);
        frame.add(colorBar);
        frame.add(toolBar);
        frame.add(infoBar);
        frame.add(map);

        frame.setLayout(null);
        frame.setVisible(true);
    }
}
