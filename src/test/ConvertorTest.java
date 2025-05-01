package com.example.mymodule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConvertorTest {
    private Convertor convertor;

    @BeforeEach
    void setUp() {
        // Инициализируем Convertor с начальными значениями перед каждым тестом
        double xMin = -5, xMax = 5, yMin = -6, yMax = 6;
        int width = 800, height = 600;
        convertor = new Convertor(xMin, xMax, yMin, yMax, width, height);
    }

    @Test
    void testGetXMin() {
        assertEquals(-5, convertor.getXMin(), "Значение Xmin должно быть -5");
    }

    @Test
    void testSetXMin() {
        convertor.setXMin(-10);
        assertEquals(-10, convertor.getXMin(), "Значение Xmin должно быть обновлено до -10");
    }

    @Test
    void testGetXMax() {
        assertEquals(5, convertor.getXMax(), "Значение Xmax должно быть 5");
    }

    @Test
    void testSetXMax() {
        convertor.setXMax(10);
        assertEquals(10, convertor.getXMax(), "Значение Xmax должно быть обновлено до 10");
    }

    @Test
    void testGetYMin() {
        assertEquals(-6, convertor.getYMin(), "Значение Ymin должно быть -6");
    }

    @Test
    void testSetYMin() {
        convertor.setYMin(-12);
        assertEquals(-12, convertor.getYMin(), "Значение Ymin должно быть обновлено до -12");
    }

    @Test
    void testGetYMax() {
        assertEquals(6, convertor.getYMax(), "Значение Ymax должно быть 6");
    }

    @Test
    void testSetYMax() {
        convertor.setYMax(12);
        assertEquals(12, convertor.getYMax(), "Значение Ymax должно быть обновлено до 12");
    }

    @Test
    void testGetWidth() {
        assertEquals(800, convertor.getWidth(), "Ширина должна быть 800");
    }

    @Test
    void testSetWidth() {
        convertor.setWidth(1024);
        assertEquals(1024, convertor.getWidth(), "Ширина должна быть обновлена до 1024");
    }

    @Test
    void testGetHeight() {
        assertEquals(600, convertor.getHeight(), "Высота должна быть 600");
    }

    @Test
    void testSetHeight() {
        convertor.setHeight(768);
        assertEquals(768, convertor.getHeight(), "Высота должна быть обновлена до 768");
    }

    @Test
    void testXCrt2Scr() {
        // Тестируем преобразование декартовых координат в экранную систему для оси X
        assertEquals(400, convertor.xCrt2Scr(0), "Центральная точка X должна быть в центре экрана (400)");
        assertEquals(0, convertor.xCrt2Scr(-5), "Минимальное значение X должно быть слева на экране (0)");
        assertEquals(800, convertor.xCrt2Scr(5), "Максимальное значение X должно быть справа на экране (800)");
    }

    @Test
    void testYCrt2Scr() {
        // Тестируем преобразование декартовых координат в экранную систему для оси Y
        assertEquals(300, convertor.yCrt2Scr(0), "Центральная точка Y должна быть в центре экрана (300)");
        assertEquals(600, convertor.yCrt2Scr(-6), "Минимальное значение Y должно быть внизу экрана (600)");
        assertEquals(0, convertor.yCrt2Scr(6), "Максимальное значение Y должно быть вверху экрана (0)");
    }
}