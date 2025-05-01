package com.example.mymodule;

import java.awt.*;

public class FunctionPainter extends CartesianPainter {
    private double[] xPoints;
    private double[] yPoints;
    private boolean showPoints = true; // Флаг для отображения точек
    private boolean showGraph = true; // Флаг для отображения графика
    private boolean showDerivative = false; // Флаг для отображения производной

    private Color graphColor = Color.BLUE; // Цвет графика
    private Color pointColor = Color.GREEN; // Цвет точек
    private Color derivativeColor = Color.RED; // Цвет производной

    public FunctionPainter(int width, int height, Convertor convertor, double[] xPoints, double[] yPoints) {
        super(width, height, convertor);
        this.xPoints = xPoints;
        this.yPoints = yPoints;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (showPoints) {
            drawPoints(g); // Рисуем точки
        }

        if (showGraph) {
            drawGraph(g); // Рисуем график
        }

        if (showDerivative) {
            drawDerivative(g); // Рисуем производную
        }
    }

    private void drawPoints(Graphics g) {
        g.setColor(pointColor);

        for (int i = 0; i < xPoints.length; i++) {
            int scrX = convertor.xCrt2Scr(xPoints[i]);
            int scrY = convertor.yCrt2Scr(yPoints[i]);
            g.fillOval(scrX - 3, scrY - 3, 6, 6);
        }
    }

    private void drawGraph(Graphics g) {
        g.setColor(graphColor);

        for (int i = 1; i < xPoints.length; i++) {
            int scrX1 = convertor.xCrt2Scr(xPoints[i - 1]);
            int scrY1 = convertor.yCrt2Scr(yPoints[i - 1]);
            int scrX2 = convertor.xCrt2Scr(xPoints[i]);
            int scrY2 = convertor.yCrt2Scr(yPoints[i]);

            g.drawLine(scrX1, scrY1, scrX2, scrY2);
        }
    }

    private void drawDerivative(Graphics g) {
        g.setColor(derivativeColor);

        for (int i = 1; i < xPoints.length; i++) {
            double derivative = (yPoints[i] - yPoints[i - 1]) / (xPoints[i] - xPoints[i - 1]);
            int scrX1 = convertor.xCrt2Scr(xPoints[i - 1]);
            int scrY1 = convertor.yCrt2Scr(yPoints[i - 1] + derivative);
            int scrX2 = convertor.xCrt2Scr(xPoints[i]);
            int scrY2 = convertor.yCrt2Scr(yPoints[i] + derivative);

            g.drawLine(scrX1, scrY1, scrX2, scrY2);
        }
    }

    public void setShowPoints(boolean showPoints) {
        this.showPoints = showPoints;
    }

    public void setShowGraph(boolean showGraph) {
        this.showGraph = showGraph;
    }

    public void setShowDerivative(boolean showDerivative) {
        this.showDerivative = showDerivative;
    }

    public Color getGraphColor() {
        return graphColor;
    }

    public void setGraphColor(Color graphColor) {
        this.graphColor = graphColor;
    }

    public Color getPointColor() {
        return pointColor;
    }

    public void setPointColor(Color pointColor) {
        this.pointColor = pointColor;
    }

    public Color getDerivativeColor() {
        return derivativeColor;
    }

    public void setDerivativeColor(Color derivativeColor) {
        this.derivativeColor = derivativeColor;
    }
}