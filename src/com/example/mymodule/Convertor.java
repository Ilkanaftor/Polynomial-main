package com.example.mymodule;

public class Convertor {
    private double xMin, xMax;
    private double yMin, yMax;
    private int width, height;

    public Convertor(double xMin, double xMax, double yMin, double yMax, int width, int height) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.width = width;
        this.height = height;
    }

    public double getXMin() {
        return xMin;
    }

    public void setXMin(double xMin) {
        this.xMin = xMin;
    }

    public double getXMax() {
        return xMax;
    }

    public void setXMax(double xMax) {
        this.xMax = xMax;
    }

    public double getYMin() {
        return yMin;
    }

    public void setYMin(double yMin) {
        this.yMin = yMin;
    }

    public double getYMax() {
        return yMax;
    }

    public void setYMax(double yMax) {
        this.yMax = yMax;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int xCrt2Scr(double crtX) {
        if (xMax == xMin) {
            return width / 2; // Защита от деления на ноль
        }
        return (int) ((crtX - xMin) * width / (xMax - xMin));
    }

    public int yCrt2Scr(double crtY) {
        if (yMax == yMin) {
            return height / 2; // Защита от деления на ноль
        }
        return (int) (height - (crtY - yMin) * height / (yMax - yMin));
    }
}