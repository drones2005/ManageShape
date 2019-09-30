package com.technoserv.mogs.panels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioPanel extends JPanel {
    private ButtonGroup group;
    private int countRadioButton = 0;
    private int selection;

    public RadioPanel(int x, int y, int width, int height) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBounds(x, y, width, height);
        setBorder(BorderFactory.createEtchedBorder());
        group = new ButtonGroup();
        addRadioButton("Треугольник", true);
        addRadioButton("Прямоугольник",false);
        addRadioButton("Эллипс",false);
    }

    public void addRadioButton(String name, boolean active){
        final int count = countRadioButton;
        JRadioButton radioButton = new JRadioButton(name, active);
        radioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selection = count;
            }
        });

        group.add(radioButton);
        add(radioButton);
        countRadioButton++;
    }

    public int getSelection() {
        return selection;
    }
}
