package com.technoserv.mogs.panels;

import com.technoserv.mogs.settings.CurrentSettings;
import com.technoserv.mogs.figures.Figure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.List;


public class MogsMenuBar extends JMenuBar {
    private JMenu fileMenu;
    private String fileName;
    private MogsMap map;
    private Logger log;

    public MogsMenuBar(CurrentSettings cs, MogsMap map) {
        this.log = LogManager.getLogger(MogsMenuBar.class);
        this.map = map;

        log.info("Создание меню: начало");
        log.info("Координаты: (" + cs.getMenu_offset_x() + "," + cs.getMenu_offset_y() + "); w = " + cs.getMenu_width() + ", h = " + cs.getMenu_height());
        try {
            setBounds(cs.getMenu_offset_x(), cs.getMenu_offset_y(), cs.getMenu_width(), cs.getMenu_height());
            fileMenu = new JMenu("Файл");
            add(fileMenu);
            fileMenu.add(addLoadMenuItem("Загрузить"));
            fileMenu.add(addSaveMenuItem("Сохранить"));
            fileMenu.add(addExitMenuItem("Выход"));

        } catch(Exception ex){
            log.info("------------------------------------------------------");
            log.info("Ошибка при создании меню " + ex.getClass());
            log.info("------------------------------------------------------");
            log.info("Создание меню: конец");
        }
        log.info("Создание меню: конец");
    }

    public JMenuItem addLoadMenuItem(String name){
        Action loadAction = new AbstractAction(name) {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                TextFileFilter binFilter = new TextFileFilter(".bin");
                fileChooser.setCurrentDirectory(new File("D:/File_figures"));

                fileChooser.addChoosableFileFilter(binFilter);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    fileName = fileChooser.getSelectedFile().getAbsolutePath();
                    File file = new File(fileName);
                    try {
                        FileInputStream fis = new FileInputStream(fileName);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        map.setFigure((List<Figure>) ois.readObject());
                        map.repaint();
                        log.info("Загрузка объектов");
                    } catch (IOException ex) {
                        log.debug("Ошибка ввода/вывода при загрузке объектов" + ex.getMessage());
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        log.debug("Ошибка при загрузке объектов" + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
        };
        return new JMenuItem(loadAction);
    }

    public JMenuItem addSaveMenuItem(String name){
        Action saveAction = new AbstractAction(name) {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("D:/File_figures"));
                if(map.getFigures().size() == 0){
                    JOptionPane.showMessageDialog(null,"объектов - 0","Пердупреждение", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (fileName == null) {
                        fileChooser.addChoosableFileFilter(new TextFileFilter(".bin"));
                        int result = fileChooser.showSaveDialog(null);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            fileName = fileChooser.getSelectedFile().getAbsolutePath();
                        }
                    }
                    try {
                        FileOutputStream fos = new FileOutputStream(fileName);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(map.getFigures());
                        log.info("Сохранение объектов");
                    } catch (IOException ex) {
                        log.info("Ошибка при сохранение объектов" + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
        };

        return new JMenuItem(saveAction);
    }

    public JMenuItem addExitMenuItem(String name){
        Action cleanAction = new AbstractAction(name) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        return new JMenuItem(cleanAction);
    }
}

class TextFileFilter extends FileFilter {
    private String ext;

    public TextFileFilter(String ext) {
        this.ext = ext;
    }

    @Override
    public boolean accept(File f) {
        if(f.isDirectory()) return true;
        return (f.getName().endsWith(ext));
    }

    @Override
    public String getDescription() {
        return "*" + ext;
    }
}
