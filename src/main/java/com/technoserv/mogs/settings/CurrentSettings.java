package com.technoserv.mogs.settings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CurrentSettings {
    private int window_width;
    private int window_height;
    private int map_width;
    private int map_height;
    private int map_offset_x;
    private int map_offset_y;
    private Color map_background;
    private int colorbar_width;
    private int colorbar_height;
    private int colorbar_offset_x;
    private int colorbar_offset_y;
    private Color colorbar_fillcolor;
    private Color colorbar_bordercolor;
    private int toolbar_width;
    private int toolbar_height;
    private int toolbar_offset_x;
    private int toolbar_offset_y;
    private int toolbar_mode;
    private int menu_width;
    private int menu_height;
    private int menu_offset_x;
    private int menu_offset_y;
    private Map<String,Integer> figures;
    private String pathpics;
    private int infobar_width;
    private int infobar_height;
    private int infobar_offset_x;
    private int infobar_offset_y;

    public CurrentSettings(String filename, Logger log) throws IOException, NoSuchFieldException, IllegalAccessException {
        Properties properties = new Properties();
//        FileInputStream fis = null;
//        fis = new FileInputStream(filename);
//        properties.load(fis);
        properties.load(getClass().getClassLoader().getResourceAsStream(filename));

        try {
            this.window_width = Integer.parseInt(properties.getProperty("window.width"));
            this.window_height = Integer.parseInt(properties.getProperty("window.height"));
            this.map_width = Integer.parseInt(properties.getProperty("map.width"));
            this.map_height = Integer.parseInt(properties.getProperty("map.height"));
            this.map_offset_x = Integer.parseInt(properties.getProperty("map.offset.x"));
            this.map_offset_y = Integer.parseInt(properties.getProperty("map.offset.y"));
            this.map_background = (Color) Color.class.getField(properties.getProperty("map.background")).get(null);
            this.colorbar_width = Integer.parseInt(properties.getProperty("colorbar.width"));
            this.colorbar_height = Integer.parseInt(properties.getProperty("colorbar.height"));
            this.colorbar_offset_x = Integer.parseInt(properties.getProperty("colorbar.offset.x"));
            this.colorbar_offset_y = Integer.parseInt(properties.getProperty("colorbar.offset.y"));
            this.colorbar_fillcolor = (Color) Color.class.getField(properties.getProperty("colorbar.fillcolor")).get(null);
            this.colorbar_bordercolor = (Color) Color.class.getField(properties.getProperty("colorbar.bordercolor")).get(null);
            this.toolbar_width = Integer.parseInt(properties.getProperty("toolbar.width"));
            this.toolbar_height = Integer.parseInt(properties.getProperty("toolbar.height"));
            this.toolbar_offset_x = Integer.parseInt(properties.getProperty("toolbar.offset.x"));
            this.toolbar_offset_y = Integer.parseInt(properties.getProperty("toolbar.offset.y"));
            this.toolbar_mode = Integer.parseInt(properties.getProperty("toolbar.mode"));
            this.menu_width = Integer.parseInt(properties.getProperty("menu.width"));
            this.menu_height = Integer.parseInt(properties.getProperty("menu.height"));
            this.menu_offset_x = Integer.parseInt(properties.getProperty("menu.offset.x"));
            this.menu_offset_y = Integer.parseInt(properties.getProperty("menu.offset.y"));
            this.figures = new HashMap<String, Integer>();
            String[] figures = properties.getProperty("figures").split(";");
            for (String figure : figures) {
                String[] keyValue = figure.split(":");
                this.figures.put(keyValue[0], Integer.parseInt(keyValue[1]));
            }
            this.pathpics = properties.getProperty("toolbar.pathpics");

            this.infobar_width = Integer.parseInt(properties.getProperty("infobar.width"));
            this.infobar_height = Integer.parseInt(properties.getProperty("infobar.height"));
            this.infobar_offset_x = Integer.parseInt(properties.getProperty("infobar.offset.x"));
            this.infobar_offset_y = Integer.parseInt(properties.getProperty("infobar.offset.y"));

        } catch(Exception ex) {
            log.info("------------------------------------------------------");
            log.info("Ошибка при загрузки параметров настройки " + ex.getClass());
            log.info("------------------------------------------------------");
        }
    }

    public int getWindow_width() {
        return window_width;
    }

    public int getWindow_height() {
        return window_height;
    }

    public int getMap_width() {
        return map_width;
    }

    public int getMap_height() {
        return map_height;
    }

    public int getMap_offset_x() {
        return map_offset_x;
    }

    public int getMap_offset_y() {
        return map_offset_y;
    }

    public Color getMap_background() {
        return map_background;
    }

    public int getColorbar_width() {
        return colorbar_width;
    }

    public int getColorbar_height() {
        return colorbar_height;
    }

    public int getColorbar_offset_x() {
        return colorbar_offset_x;
    }

    public int getColorbar_offset_y() {
        return colorbar_offset_y;
    }

    public Color getColorbar_fillcolor() {
        return colorbar_fillcolor;
    }

    public void setColorbar_fillcolor(Color colorbar_fillcolor) {
        this.colorbar_fillcolor = colorbar_fillcolor;
    }

    public Color getColorbar_bordercolor() {
        return colorbar_bordercolor;
    }

    public void setColorbar_bordercolor(Color colorbar_bordercolor) {
        this.colorbar_bordercolor = colorbar_bordercolor;
    }

    public int getToolbar_width() {
        return toolbar_width;
    }

    public int getToolbar_height() {
        return toolbar_height;
    }

    public int getToolbar_offset_x() {
        return toolbar_offset_x;
    }

    public int getToolbar_offset_y() {
        return toolbar_offset_y;
    }

    public int getToolbar_mode() {
        return toolbar_mode;
    }

    public void setToolbar_mode(int toolbar_mode) {
        this.toolbar_mode = toolbar_mode;
    }

    public int getMenu_width() {
        return menu_width;
    }

    public int getMenu_height() {
        return menu_height;
    }

    public int getMenu_offset_x() {
        return menu_offset_x;
    }

    public int getMenu_offset_y() {
        return menu_offset_y;
    }

    public Map<String, Integer> getFigures() {
        return figures;
    }

    public String getPathpics() {
        return pathpics;
    }

    public int getInfobar_width() {
        return infobar_width;
    }

    public int getInfobar_height() {
        return infobar_height;
    }

    public int getInfobar_offset_x() {
        return infobar_offset_x;
    }

    public int getInfobar_offset_y() {
        return infobar_offset_y;
    }
}
