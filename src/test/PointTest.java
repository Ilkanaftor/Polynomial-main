package math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Locale;

public class PointTest {

    private static final double EPSILON = 1e-9; // Точность сравнения чисел с плавающей точкой

    @Test
    void testConstructorAndGetters() {
        Point point = new Point(3.0, 4.0);
        assertEquals(3.0, point.getX(), "X координата должна быть 3.0");
        assertEquals(4.0, point.getY(), "Y координата должна быть 4.0");
    }

    @Test
    void testEqualsWithIdenticalPoints() {
        Point point1 = new Point(3.0, 4.0);
        Point point2 = new Point(3.0, 4.0);
        assertTrue(point1.equals(point2), "Точки с одинаковыми координатами должны быть равны");
    }

    @Test
    void testEqualsWithDifferentPoints() {
        Point point1 = new Point(3.0, 4.0);
        Point point2 = new Point(5.0, 6.0);
        assertFalse(point1.equals(point2), "Точки с разными координатами не должны быть равны");
    }

    @Test
    void testEqualsWithEpsilonDifference() {
        Point point1 = new Point(3.0, 4.0);
        Point point2 = new Point(3.0 + 0.9 * EPSILON, 4.0 - 0.9 * EPSILON);
        assertFalse(point1.equals(point2), "Точки, отличающиеся на величину меньше EPSILON, должны быть равны");
    }

    @Test
    void testEqualsWithNull() {
        Point point = new Point(3.0, 4.0);
        assertFalse(point.equals(null), "Точка не должна быть равна null");
    }

    @Test
    void testEqualsWithDifferentClass() {
        Point point = new Point(3.0, 4.0);
        Object obj = new Object();
        assertFalse(point.equals(obj), "Точка не должна быть равна объекту другого класса");
    }

    @Test
    void testHashCodeForIdenticalPoints() {
        Point point1 = new Point(3.0, 4.0);
        Point point2 = new Point(3.0, 4.0);
        assertEquals(point1.hashCode(), point2.hashCode(), "Хэш-коды одинаковых точек должны совпадать");
    }

    @Test
    void testHashCodeForDifferentPoints() {
        Point point1 = new Point(3.0, 4.0);
        Point point2 = new Point(5.0, 6.0);
        assertNotEquals(point1.hashCode(), point2.hashCode(), "Хэш-коды разных точек могут отличаться");
    }

    @Test
    void testToString() {
        Point point = new Point(3.14159, 2.71828);
        String expected = String.format(Locale.US, "(%.2f, %.2f)", 3.14159, 2.71828);
        assertEquals(expected, point.toString(), "Строковое представление должно соответствовать формату (x.xx, y.yy)");
    }
}