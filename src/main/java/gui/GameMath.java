package gui;

public class GameMath {
    static final double TWO_PI = 2 * Math.PI;

    static double distance(double x1, double y1, double x2, double y2) {
        double diffX = x1 - x2;
        double diffY = y1 - y2;
        return Math.sqrt(diffX * diffX + diffY * diffY);
    }

    static double angleTo(double fromX, double fromY, double toX, double toY) {
        return Math.atan2(toY - fromY, toX - fromX);
    }

    static double asNormalizedRadians(double angle) {
        angle = angle % TWO_PI;
        return angle >= 0 ? angle : angle + TWO_PI;
    }

    static double minByModulus(double a, double b) {
        return Math.abs(a) < Math.abs(b) ? a : b;
    }

    static double applyLimits(double value, double min, double max) {
        return value < min ? min : value > max ? max : value;
    }
}