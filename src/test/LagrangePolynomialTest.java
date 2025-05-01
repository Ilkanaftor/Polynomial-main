package math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class LagrangePolynomialTest {

    @Test
    void testConstructorAndDegree() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 2));
        points.add(new Point(2, 3));
        LagrangePolynomial poly = new LagrangePolynomial(points);
        assertEquals(1, poly.degree(), "Степень полинома должна быть n-1");
    }

    @Test
    void testEvaluate() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 2));
        points.add(new Point(2, 3));
        LagrangePolynomial poly = new LagrangePolynomial(points);
        assertEquals(2.0, poly.evaluate(1), 0.0001, "Значение в точке x=1 должно быть 2.0");
    }

    @Test
    void testAddPoint() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 2));
        LagrangePolynomial poly = new LagrangePolynomial(points);
        poly.addPoint(new Point(2, 3));
        assertEquals(1, poly.degree(), "Степень полинома должна обновиться");
    }

    @Test
    void testToString() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 2));
        points.add(new Point(2, 3));
        LagrangePolynomial poly = new LagrangePolynomial(points);
        assertNotNull(poly.toString(), "Строковое представление не должно быть null");
    }

    @Test
    void testDuplicateXThrowsException() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 2));
        LagrangePolynomial poly = new LagrangePolynomial(points);
        assertThrows(IllegalArgumentException.class, () -> poly.addPoint(new Point(1, 3)));
    }
}