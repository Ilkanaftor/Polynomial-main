package math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class PolynomialTest {

    @Test
    void testConstructorAndDegree() {
        // Пустой полином
        Polynomial poly = new Polynomial();
        assertEquals(0, poly.degree(), "Степень пустого полинома должна быть 0");

        // Полином с коэффициентами [1, 2, 3]
        List<Double> coefficients = List.of(1.0, 2.0, 3.0);
        Polynomial poly2 = new Polynomial(coefficients);
        assertEquals(2, poly2.degree(), "Степень полинома должна быть 2");

        // Полином с нулевыми старшими коэффициентами
        List<Double> coefficientsWithZeros = List.of(1.0, 2.0, 0.0, 0.0);
        Polynomial poly3 = new Polynomial(coefficientsWithZeros);
        assertEquals(1, poly3.degree(), "Степень полинома должна быть 1 после удаления нулевых коэффициентов");
        
        List<Double> c = new ArrayList<>();
        Polynomial poly4 = new Polynomial(c);
        assertEquals(List.of(0.0), poly4.getCoefficients());
    }

    @Test
    void testEvaluate() {
        // Полином p(x) = 1 + 2x + 3x^2
        List<Double> coefficients = List.of(1.0, 2.0, 3.0);
        Polynomial poly = new Polynomial(coefficients);

        // Проверяем значение в точке x=0
        assertEquals(1.0, poly.evaluate(0), 0.0001, "Значение в точке x=0 должно быть 1.0");

        // Проверяем значение в точке x=1
        assertEquals(6.0, poly.evaluate(1), 0.0001, "Значение в точке x=1 должно быть 6.0");

        // Проверяем значение в точке x=-1
        assertEquals(2.0, poly.evaluate(-1), 0.0001, "Значение в точке x=-1 должно быть 2.0");
    }

    @Test
    void testAdd() {
        // Полином p1(x) = 1 + 2x + 3x^2
        List<Double> coefficients1 = List.of(1.0, 2.0, 3.0);
        Polynomial p1 = new Polynomial(coefficients1);

        // Полином p2(x) = 4 + 5x
        List<Double> coefficients2 = List.of(4.0, 5.0);
        Polynomial p2 = new Polynomial(coefficients2);

        // Сумма: p1(x) + p2(x) = 5 + 7x + 3x^2
        Polynomial sum = p1.add(p2);
        assertEquals(2, sum.degree(), "Степень суммы должна быть 2");
        assertEquals(List.of(5.0, 7.0, 3.0), sum.getCoefficients(), "Коэффициенты суммы неверны");
    }

    @Test
    void testSubtract() {
        // Полином p1(x) = 1 + 2x + 3x^2
        List<Double> coefficients1 = List.of(1.0, 2.0, 3.0);
        Polynomial p1 = new Polynomial(coefficients1);

        // Полином p2(x) = 4 + 5x
        List<Double> coefficients2 = List.of(4.0, 5.0);
        Polynomial p2 = new Polynomial(coefficients2);

        // Разность: p1(x) - p2(x) = -3 - 3x + 3x^2
        Polynomial diff = p1.subtract(p2);
        assertEquals(2, diff.degree(), "Степень разности должна быть 2");
        assertEquals(List.of(-3.0, -3.0, 3.0), diff.getCoefficients(), "Коэффициенты разности неверны");
    }

    @Test
    void testMultiply() {
        // Полином p1(x) = 1 + 2x
        List<Double> coefficients1 = List.of(1.0, 2.0);
        Polynomial p1 = new Polynomial(coefficients1);

        // Полином p2(x) = 3 + 4x
        List<Double> coefficients2 = List.of(3.0, 4.0);
        Polynomial p2 = new Polynomial(coefficients2);

        // Произведение: p1(x) * p2(x) = 3 + 10x + 8x^2
        Polynomial product = p1.multiply(p2);
        assertEquals(2, product.degree(), "Степень произведения должна быть 2");
        assertEquals(List.of(3.0, 10.0, 8.0), product.getCoefficients(), "Коэффициенты произведения неверны");
    }

    @Test
    void testMultiplyByScalar() {
        // Полином p(x) = 1 + 2x + 3x^2
        List<Double> coefficients = List.of(1.0, 2.0, 3.0);
        Polynomial poly = new Polynomial(coefficients);

        // Умножение на скаляр 2
        Polynomial scaled = poly.multiply(2);
        assertEquals(2, scaled.degree(), "Степень должна остаться 2");
        assertEquals(List.of(2.0, 4.0, 6.0), scaled.getCoefficients(), "Коэффициенты после умножения неверны");
    }

    @Test
    void testDivideByScalar() {
        // Полином p(x) = 2 + 4x + 6x^2
        List<Double> coefficients = List.of(2.0, 4.0, 6.0);
        Polynomial poly = new Polynomial(coefficients);

        // Деление на скаляр 2
        Polynomial divided = poly.divide(2);
        assertEquals(2, divided.degree(), "Степень должна остаться 2");
        assertEquals(List.of(1.0, 2.0, 3.0), divided.getCoefficients(), "Коэффициенты после деления неверны");

        // Проверка деления на ноль
        assertThrows(IllegalArgumentException.class, () -> poly.divide(0), "Деление на ноль должно вызывать исключение");
    }

    @Test
    void testToString() {
        // Полином p(x) = 1 + 2x + 3x^2
        List<Double> coefficients = List.of(1.0, 2.0, 3.0);
        Polynomial poly = new Polynomial(coefficients);

        // Проверяем строковое представление
        String expected = "3.00x^2 + 2.00x + 1.00";
        assertEquals(expected, poly.toString(), "Строковое представление неверно");

        // Константный полином
        Polynomial constantPoly = new Polynomial(List.of(5.0));
        assertEquals("5.00", constantPoly.toString(), "Строковое представление константного полинома неверно");
    }

    @Test
    void testEqualsAndHashCode() {
        // Полином p1(x) = 1 + 2x + 3x^2
        List<Double> coefficients1 = List.of(1.0, 2.0, 3.0);
        Polynomial p1 = new Polynomial(coefficients1);

        // Полином p2(x) = 1 + 2x + 3x^2
        List<Double> coefficients2 = List.of(1.0, 2.0, 3.0);
        Polynomial p2 = new Polynomial(coefficients2);

        // Полином p3(x) = 1 + 2x
        List<Double> coefficients3 = List.of(1.0, 2.0);
        Polynomial p3 = new Polynomial(coefficients3);

        // Проверяем равенство полиномов
        assertTrue(p1.equals(p2), "Полиномы с одинаковыми коэффициентами должны быть равны");
        assertFalse(p1.equals(p3), "Полиномы с разными коэффициентами не должны быть равны");

        // Проверяем хэш-коды
        assertEquals(p1.hashCode(), p2.hashCode(), "Равные полиномы должны иметь одинаковые хэш-коды");
        assertNotEquals(p1.hashCode(), p3.hashCode(), "Разные полиномы должны иметь разные хэш-коды");
    }
}