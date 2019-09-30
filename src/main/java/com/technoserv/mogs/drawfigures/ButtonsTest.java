package com.technoserv.mogs.drawfigures;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ButtonsTest extends JFrame
{
    private static final long serialVersionUID = 1L;

    private JToggleButton button;
    private JPanel panelRadio, panelCheck;

    public ButtonsTest()
    {
        super("Кнопки управления");
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        // Панель содержимого
        Container container = getContentPane();
        // Последовательное расположение
        container.setLayout(new FlowLayout());
        // Создание кнопки JToggleButton
        button = new JToggleButton("Нажмите", false);
        // Слушатель события о смене состояния
        button.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String text = (button.isSelected()) ? "Отпустите" :
                        "Нажмите";
                button.setText(text);
                panelRadio.setVisible(!button.isSelected());
                panelCheck.setVisible( button.isSelected());
            }
        });

        // Группа связанных радио-переключателей
        panelRadio = new JPanel(new GridLayout(0, 1, 0, 5));
        panelRadio.setBorder(BorderFactory.createTitledBorder("Напитки"));
        String[] names1 = { "Байкал", "Тархун", "Минеральная" };
        ButtonGroup bg1 = new ButtonGroup();
        for (int i = 0; i < names1.length; i++) {
            JRadioButton radio = new JRadioButton(names1[i]);
            panelRadio.add(radio);
            bg1.add(radio);
        }

        // Группа связанных флажков
        panelCheck = new JPanel(new GridLayout(0, 1, 0, 5));
        panelCheck.setBorder(BorderFactory.createTitledBorder("Мороженое"));
        String[] names2 = { "Шоколадное", "Крем-брюле", "Пломбир"   };
        ButtonGroup bg2 = new ButtonGroup();
        for (int i = 0; i < names2.length; i++) {
            JCheckBox check = new JCheckBox(names2[i]);
            panelCheck.add(check);
            bg2.add(check);
        }
        panelCheck.setVisible(false);

        // Добавляем компоненты в контейнер
        container.add(button);
        container.add(panelRadio);
        container.add(panelCheck);

        // Открываем окно
        setSize(280, 170);
        setVisible(true);
    }
    //--------------------------------------
    public static void main(String[] args) {
        new ButtonsTest();
    }
}