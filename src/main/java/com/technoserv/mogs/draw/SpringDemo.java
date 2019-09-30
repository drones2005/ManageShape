package com.technoserv.mogs.draw;

import javax.swing.SpringLayout;
import javax.swing.Spring;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Container;
import java.awt.Component;

public class SpringDemo {
    private static void createAndShowGUI() {
        // Создаем окно
        JFrame frame = new JFrame("SpringDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Устанавливаем менеджер SpringLayout
        Container contentPane = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        // Создание компонентов
        JLabel label = new JLabel("Label: ");
        JTextField textField = new JTextField("Text field", 15);
        contentPane.add(label);
        contentPane.add(textField);

        // Делаем ограничения для label
        SpringLayout.Constraints contentPaneCons =
                layout.getConstraints(contentPane);
        contentPaneCons.setX(Spring.sum(Spring.constant(5),
                contentPaneCons
                        .getConstraint(SpringLayout.WEST)));
        contentPaneCons.setY(Spring.sum(Spring.constant(5),
                contentPaneCons
                        .getConstraint(SpringLayout.NORTH)));

        // Делаем ограничения для text field
        SpringLayout.Constraints textFieldCons =
                layout.getConstraints(textField);
        textFieldCons.setX(Spring.sum(
                Spring.constant(5),
                contentPaneCons.getConstraint(SpringLayout.EAST)));
        textFieldCons.setY(Spring.constant(5));
        setContainerSize(contentPane, 5);

        // Делаем окно видемым
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void setContainerSize(Container parent, int pad) {
        SpringLayout layout = (SpringLayout) parent.getLayout();
        Component[] components = parent.getComponents();
        Spring maxHeightSpring = Spring.constant(0);
        SpringLayout.Constraints pCons = layout.getConstraints(parent);

        // устанавливаем контейнеры в правый край
        // с его rightmost компонентом +дополнения.
        Component rightmost = components[components.length - 1];
        SpringLayout.Constraints rCons =
                layout.getConstraints(rightmost);
        pCons.setConstraint(
                SpringLayout.EAST,
                Spring.sum(Spring.constant(pad),
                        rCons.getConstraint(SpringLayout.EAST)));

        // устанавливаем контейнеры в нижний край
        // с его компонентом +дополнения.
        for (int i = 0; i < components.length; i++) {
            SpringLayout.Constraints cons =
                    layout.getConstraints(components[i]);
            maxHeightSpring = Spring.max(maxHeightSpring,
                    cons.getConstraint(
                            SpringLayout.SOUTH));
        }
        pCons.setConstraint(
                SpringLayout.SOUTH,
                Spring.sum(Spring.constant(pad),
                        maxHeightSpring));
    }
}

