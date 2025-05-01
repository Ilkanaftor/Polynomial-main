package com.example.mymodule;

import java.awt.*;

public class CartesianPainter implements Painter {
    protected int width, height;
    protected Convertor convertor;

    public CartesianPainter(int width, int height, Convertor convertor) {
        this.width = width;
        this.height = height;
        this.convertor = convertor;
    }

    @Override
    public Dimension getSize() {
        return new Dimension(width, height);
    }

    @Override
    public void setSize(Dimension d) {
        this.width = d.width;
        this.height = d.height;
    }

    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void paint(Graphics g) {
        drawAxes(g);      // Рисуем оси координат
        drawDivisions(g); // Рисуем деления на осях
    }

    private void drawAxes(Graphics g) {
        g.setColor(Color.BLACK);

        // Ось X (горизонтальная)
        int scrY = convertor.yCrt2Scr(0); // Центр по Y
        scrY = Math.max(0, Math.min(height, scrY)); // Ограничиваем границы
        g.drawLine(0, scrY, width, scrY);

        // Ось Y (вертикальная)
        int scrX = convertor.xCrt2Scr(0); // Центр по X
        scrX = Math.max(0, Math.min(width, scrX)); // Ограничиваем границы
        g.drawLine(scrX, 0, scrX, height);
    }

    private void drawDivisions(Graphics g) {
        g.setColor(Color.GRAY);

        // Деления на оси X
        for (double x = convertor.getXMin(); x <= convertor.getXMax(); x += 0.5) {
            int scrX = convertor.xCrt2Scr(x);
            if (scrX >= 0 && scrX < width) { // Проверяем, что черточка находится внутри окна
                int scrY = convertor.yCrt2Scr(0); // Центр по Y
                scrY = Math.max(0, Math.min(height, scrY)); // Ограничиваем границы
                g.drawLine(scrX, scrY - 5, scrX, scrY + 5); // Короткие вертикальные черточки
            }
        }

        // Деления на оси Y
        for (double y = convertor.getYMin(); y <= convertor.getYMax(); y += 0.5) {
            int scrY = convertor.yCrt2Scr(y);
            if (scrY >= 0 && scrY < height) { // Проверяем, что черточка находится внутри окна
                int scrX = convertor.xCrt2Scr(0); // Центр по X
                scrX = Math.max(0, Math.min(width, scrX)); // Ограничиваем границы
                g.drawLine(scrX - 5, scrY, scrX + 5, scrY); // Короткие горизонтальные черточки
            }
        }
    }
}