package math;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class NewtonPolynomialTest {

    @Test
    void testConstructorAndDegree() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 2));
        points.add(new Point(2, 3));
        points.add(new Point(3, 5));

        NewtonPolynomial poly = new NewtonPolynomial(points);
        assertEquals(2, poly.degree(), "Степень полинома должна быть n-1");
    }

    @Test
    void testEvaluate() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 2));
        points.add(new Point(2, 3));
        points.add(new Point(3, 5));

        NewtonPolynomial poly = new NewtonPolynomial(points);

        assertEquals(2.0, poly.evaluate(1), 0.0001, "Значение в точке x=1 должно быть 2.0");
        assertEquals(3.0, poly.evaluate(2), 0.0001, "Значение в точке x=2 должно быть 3.0");
        assertEquals(5.0, poly.evaluate(3), 0.0001, "Значение в точке x=3 должно быть 5.0");

        double result = poly.evaluate(1.5);
        assertFalse(result > 2 && result < 3, "Значение полинома должно быть между y=2 и y=3 для x=1.5");
    }

    @Test
    void testAddPoint() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 2));
        points.add(new Point(2, 3));

        NewtonPolynomial poly = new NewtonPolynomial(points);
        poly.addPoint(new Point(3, 5));

        assertEquals(2, poly.degree(), "Степень полинома должна обновиться");
        assertEquals(5.0, poly.evaluate(3), 0.0001, "Значение в точке x=3 должно быть 5.0");
    }

    @Test
    void testAddPoints() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 2));

        NewtonPolynomial poly = new NewtonPolynomial(points);

        List<Point> newPoints = new ArrayList<>();
        newPoints.add(new Point(2, 3));
        newPoints.add(new Point(3, 5));
        poly.addPoints(newPoints);

        assertEquals(2, poly.degree(), "Степень полинома должна обновиться");
        assertEquals(3.0, poly.evaluate(2), 0.0001, "Значение в точке x=2 должно быть 3.0");
        assertEquals(5.0, poly.evaluate(3), 0.0001, "Значение в точке x=3 должно быть 5.0");
    }

    @Test
    void testGetCoefficients() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 2));
        points.add(new Point(2, 3));
        points.add(new Point(3, 5));

        NewtonPolynomial poly = new NewtonPolynomial(points);

        List<Double> coefficients = poly.getCoefficients();
        assertNotNull(coefficients, "Коэффициенты не должны быть null");
        assertEquals(3, coefficients.size(), "Количество коэффициентов должно быть degree + 1");
    }

    @Test
    void testToString() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 2));
        points.add(new Point(2, 3));
        points.add(new Point(3, 5));

        NewtonPolynomial poly = new NewtonPolynomial(points);

        String polyString = poly.toString();
        assertNotNull(polyString, "Строковое представление не должно быть null");
        assertTrue(polyString.contains("x"), "Строковое представление должно содержать переменную x");
        System.out.println("Полином: " + polyString);
    }

    @Test
    void testDuplicateXThrowsException() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 2));
        points.add(new Point(2, 3));

        NewtonPolynomial poly = new NewtonPolynomial(points);

        assertThrows(IllegalArgumentException.class, () -> poly.addPoint(new Point(1, 4)));
    }
}