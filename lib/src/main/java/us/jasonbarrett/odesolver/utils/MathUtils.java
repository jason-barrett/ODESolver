package us.jasonbarrett.odesolver.utils;

import java.util.Vector;

public class MathUtils {

    public static boolean isDifferenceWithinTolerance(double n1, double n2, double tolerance) {
        if (Math.abs(n1 - n2) < tolerance) {
            return true;
        }
        return false;
    }

    public static double scaledVectorDistance(Vector<Double> v1, Vector<Double> v2) throws IllegalArgumentException {
        if( v1.size() != v2.size() ) {
            throw new IllegalArgumentException("Can't compute distance between vectors of unequal size");
        }

        double squaredDifferences = 0.0;
        for( int i = 0; i < v1.size(); i++ ) {
            squaredDifferences += Math.pow((v1.get(i) - v2.get(i)), 2);
        }

        return Math.sqrt(squaredDifferences / v1.size());
    }
}
