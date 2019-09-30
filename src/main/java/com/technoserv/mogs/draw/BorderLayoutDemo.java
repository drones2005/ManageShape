package com.technoserv.mogs.draw;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BorderLayoutDemo {
    public static void main(String[] args) {
        // создаем фрейм и устанавливаем его размер.
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // создаем панель.
        JPanel p = new JPanel();
        jf.add(p);

        // к панели добавляем менеджер BorderLayout.
        p.setLayout(new BorderLayout());

        // к панели добавляем кнопку и устанавливаем для нее менеджер в верхнее расположение.
        p.add(new JButton("Okay"), BorderLayout.NORTH);

        jf.setSize(400, 300);
        jf.setVisible(true);
    }
}
