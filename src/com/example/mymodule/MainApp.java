package com.example.mymodule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainApp {
    public static void main(String[] args) {
        // Создаем экземпляр Convertor с начальными значениями
        double xMin = -5, xMax = 5, yMin = -6, yMax = 5;
        int width = 800, height = 600;
        Convertor convertor = new Convertor(xMin, xMax, yMin, yMax, width, height);

        // Массивы точек для графика
        double[] xPoints = {-4.0, -3.0, -2.0, -1.0, 0.0, 1.0, 2.0, 3.0, 4.0};
        double[] yPoints = {-0.9, -0.7, -0.4, -0.1, 0.0, 0.1, 0.4, 0.7, 0.9};

        // Создаем объект FunctionPainter
        FunctionPainter painter = new FunctionPainter(width, height, convertor, xPoints, yPoints);

        // Создаем JPanel для отрисовки
        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                painter.paint(g); // Вызываем метод paint
            }
        };

        // Настройка JFrame
        JFrame frame = new JFrame("График интерполяционного полинома");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height + 350); // Больше места под панель управления

        // Установка общей темы
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Добавляем панель для отрисовки
        frame.add(drawingPanel, BorderLayout.CENTER);

        // Добавляем панель управления
        JPanel controlPanel = new GradientPanel(new BorderLayout());
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Внешние отступы

        // Панель для настроек графика
        JPanel settingsPanel = createSettingsPanel(convertor, painter, drawingPanel);

        // Панель для выбора цветов
        JPanel colorPanel = createColorPanel(painter, drawingPanel);

        // Объединяем панели
        controlPanel.add(settingsPanel, BorderLayout.NORTH);
        controlPanel.add(colorPanel, BorderLayout.SOUTH);

        // Добавляем панель управления внизу
        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static JPanel createSettingsPanel(Convertor convertor, FunctionPainter painter, JPanel drawingPanel) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Используем GridBagLayout для табличной компоновки
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Настройки графика", 1, 0, new Font("Arial", Font.BOLD, 14)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Отступы между элементами
        gbc.anchor = GridBagConstraints.WEST; // Выравнивание по левому краю

        // Текстовые поля для ввода Xmin, Xmax, Ymin, Ymax
        JTextField txtXmin = createTextField(Double.toString(convertor.getXMin()));
        JTextField txtXmax = createTextField(Double.toString(convertor.getXMax()));
        JTextField txtYmin = createTextField(Double.toString(convertor.getYMin()));
        JTextField txtYmax = createTextField(Double.toString(convertor.getYMax()));

        // Кнопка "Обновить"
        JButton btnUpdate = new JButton("Обновить");
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 12));
        btnUpdate.setBackground(new Color(0, 128, 255));
        btnUpdate.setForeground(Color.WHITE);

        // Флажки для управления отображением
        JCheckBox chkShowPoints = createCheckBox("Показать точки", true);
        JCheckBox chkShowGraph = createCheckBox("Показать график", true);
        JCheckBox chkShowDerivative = createCheckBox("Показать производную", false);

        // Добавляем элементы в панель
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(createLabel("X min:"), gbc);

        gbc.gridx = 1;
        panel.add(txtXmin, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(createLabel("X max:"), gbc);

        gbc.gridx = 1;
        panel.add(txtXmax, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(createLabel("Y min:"), gbc);

        gbc.gridx = 1;
        panel.add(txtYmin, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(createLabel("Y max:"), gbc);

        gbc.gridx = 1;
        panel.add(txtYmax, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Занимает две колонки
        panel.add(btnUpdate, gbc);

        gbc.gridy = 5;
        panel.add(chkShowPoints, gbc);

        gbc.gridy = 6;
        panel.add(chkShowGraph, gbc);

        gbc.gridy = 7;
        panel.add(chkShowDerivative, gbc);

        // Обработчик события для кнопки "Обновить"
        btnUpdate.addActionListener(e -> {
            try {
                double newXMin = Double.parseDouble(txtXmin.getText());
                double newXMax = Double.parseDouble(txtXmax.getText());
                double newYMin = Double.parseDouble(txtYmin.getText());
                double newYMax = Double.parseDouble(txtYmax.getText());

                convertor.setXMin(newXMin);
                convertor.setXMax(newXMax);
                convertor.setYMin(newYMin);
                convertor.setYMax(newYMax);

                drawingPanel.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Неверный формат числа", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Слушатели для флажков
        chkShowPoints.addActionListener(e -> {
            painter.setShowPoints(chkShowPoints.isSelected());
            drawingPanel.repaint();
        });

        chkShowGraph.addActionListener(e -> {
            painter.setShowGraph(chkShowGraph.isSelected());
            drawingPanel.repaint();
        });

        chkShowDerivative.addActionListener(e -> {
            painter.setShowDerivative(chkShowDerivative.isSelected());
            drawingPanel.repaint();
        });

        return panel;
    }

    private static JPanel createColorPanel(FunctionPainter painter, JPanel drawingPanel) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10)); // 3 строки, 2 столбца, отступы 10px
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Настройки цвета", 1, 0, new Font("Arial", Font.BOLD, 14)));

        // Кнопки для выбора цвета
        JButton btnChooseGraphColor = createColorButton("Цвет графика", painter.getGraphColor());
        JButton btnChoosePointColor = createColorButton("Цвет точек", painter.getPointColor());
        JButton btnChooseDerivativeColor = createColorButton("Цвет производной", painter.getDerivativeColor());

        // Добавляем элементы в панель
        panel.add(btnChooseGraphColor);
        panel.add(btnChoosePointColor);
        panel.add(btnChooseDerivativeColor);

        // Слушатели для кнопок выбора цвета
        btnChooseGraphColor.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(null, "Выберите цвет графика", painter.getGraphColor());
            if (newColor != null) {
                painter.setGraphColor(newColor);
                drawingPanel.repaint();
            }
        });

        btnChoosePointColor.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(null, "Выберите цвет точек", painter.getPointColor());
            if (newColor != null) {
                painter.setPointColor(newColor);
                drawingPanel.repaint();
            }
        });

        btnChooseDerivativeColor.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(null, "Выберите цвет производной", painter.getDerivativeColor());
            if (newColor != null) {
                painter.setDerivativeColor(newColor);
                drawingPanel.repaint();
            }
        });

        return panel;
    }

    private static JTextField createTextField(String text) {
        JTextField textField = new JTextField(text, 6);
        textField.setFont(new Font("Arial", Font.PLAIN, 12));
        textField.setHorizontalAlignment(JTextField.CENTER);
        return textField;
    }

    private static JCheckBox createCheckBox(String text, boolean selected) {
        JCheckBox checkBox = new JCheckBox(text, selected);
        checkBox.setFont(new Font("Arial", Font.PLAIN, 12));
        return checkBox;
    }

    private static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        return label;
    }

    private static JButton createColorButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        return button;
    }

    // Панель с градиентным фоном
    static class GradientPanel extends JPanel {
        public GradientPanel(LayoutManager layout) {
            super(layout);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gradient = new GradientPaint(0, 0, new Color(240, 248, 255), getWidth(), getHeight(), new Color(220, 230, 240));
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }
    }
}