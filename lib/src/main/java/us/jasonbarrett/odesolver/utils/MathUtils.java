package us.jasonbarrett.odesolver.utils;

import java.util.Vector;

/*
This is a package of utility functions that perform some common mathematical tests and operations, useful in
numerical ODE solutions.
 */
public class MathUtils {

    /**
     * This method determines whether two double-precision numbers are 'equal' to within a given tolerance.
     * @param n1  The first number
     * @param n2  The second number
     * @param tolerance  The tolerance to use
     * @return true if the two numbers differ by no more than the tolerance given, false otherwise
     */
    public static boolean isDifferenceWithinTolerance(double n1, double n2, double tolerance) {
        if (Math.abs(n1 - n2) < tolerance) {
            return true;
        }
        return false;
    }

    /**
     * This method computes the distance between two vectors.
     * @param v1  The first vector
     * @param v2  The second vector
     * @return  The Euclidean distance between the vectors
     * @throws IllegalArgumentException
     */
    public static double vectorDistance(Vector<Double> v1, Vector<Double> v2) throws IllegalArgumentException {
        if( v1.size() != v2.size() ) {
            throw new IllegalArgumentException("Can't compute distance between vectors of unequal size");
        }

        double squaredDifferences = 0.0;
        for( int i = 0; i < v1.size(); i++ ) {
            squaredDifferences += Math.pow((v1.get(i) - v2.get(i)), 2);
        }

        return Math.sqrt(squaredDifferences);
    }
    /**
     * This method computes the distance between two vectors, scaled to the size of the vectors.
     * @param v1  The first vector
     * @param v2  The second vector
     * @return  The Euclidean distance between the vectors, normalized by the size of the vectors
     * @throws IllegalArgumentException
     */
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
