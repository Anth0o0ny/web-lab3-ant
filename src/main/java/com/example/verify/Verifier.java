package com.example.verify;


/**
 * Класс Verifier предоставляет методы для проверки попадания точки в заданные квадранты
 * в декартовой системе координат.
 */
public class Verifier {


    /**
     * Проверяет, попадает ли точка в область первого квадранта.
     *
     * @param x Координата x точки.
     * @param y Координата y точки.
     * @param r Радиус области.
     * @return true, если точка попадает в первый квадрант, в противном случае - false.
     */
    public boolean verifyHit(double x, double y, double r) {
        return verifyFirstQuater(x, y, r) || verifySecondQuater(x, y, r) || verifyThirdQuater(x, y, r) || verifyFourthQuater(x, y, r);
    }

    /**
     * Проверяет, попадает ли точка в область первого квадранта.
     *
     * @param x Координата x точки.
     * @param y Координата y точки.
     * @param r Радиус области.
     * @return true, если точка попадает в первый квадрант, в противном случае - false.
     */
    private boolean verifyFirstQuater(final double x, final double y, final double r) {
        return (x >= 0 && y >= 0) && (y <= (-x / 2 + r / 2d));
    }


    /**
     * Проверяет, попадает ли точка в область второго квадранта.
     *
     * @param x Координата x точки.
     * @param y Координата y точки.
     * @param r Радиус области.
     * @return true, если точка попадает во второй квадрант, в противном случае - false.
     */
    private boolean verifySecondQuater(final double x, final double y, final double r) {
        return (x <= 0 && y >= 0) && (y <= r  && x >= -r);
    }

    /**
     * Метод для проверки попадания точки в область третьего квадранта.
     * В данной реализации всегда возвращает false, так как третий квадрант не используется.
     *
     * @param x Координата x точки.
     * @param y Координата y точки.
     * @param r Радиус области.
     * @return false (так как третий квадрант не используется).
     */
    private boolean verifyThirdQuater(final double x, final double y, final double r) {
        return false;
    }


    /**
     * Проверяет, попадает ли точка в область четвертого квадранта.
     *
     * @param x Координата x точки.
     * @param y Координата y точки.
     * @param r Радиус области.
     * @return true, если точка попадает в четвертый квадрант, в противном случае - false.
     */
    private boolean verifyFourthQuater(final double x, final double y, final double r) {
        return (x >= 0 && y <= 0) && (x * x + y * y <= (r * r));
    }

}