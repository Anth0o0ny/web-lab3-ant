package com.example.verify;

public class Verifier {

    public boolean verifyHit(double x, double y, double r) {
        return verifyFirstQuater(x, y, r) || verifySecondQuater(x, y, r) || verifyThirdQuater(x, y, r) || verifyFourthQuater(x, y, r);
    }

    private boolean verifyFirstQuater(final double x, final double y, final double r) {
        return (x >= 0 && y >= 0) && (y <= (-x / 2 + r / 2d));
    }

    private boolean verifySecondQuater(final double x, final double y, final double r) {
        return (x <= 0 && y >= 0) && (y <= r  && x >= -r);
    }

    private boolean verifyThirdQuater(final double x, final double y, final double r) {
        return false;
    }

    private boolean verifyFourthQuater(final double x, final double y, final double r) {
        return (x >= 0 && y <= 0) && (x * x + y * y <= (r * r));
    }

}