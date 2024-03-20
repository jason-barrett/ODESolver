package us.jasonbarrett.odesolver.system;

import java.util.Vector;
import java.util.function.Function;

public interface ODESystem {
    /**
     * A system of ordinary differential equations (ODEs) is defined as a set of differential
     * equations of the form (example in three dimensions):
     *
     * x' = f(x, y, z)
     * y' = g(x, y, z)
     * z' = h(x, y, z);
     *
     * This method provides a function that executes these equations to transform a point
     * (x, y, z) into the derivative (x', y', z').
     *
     * @param point  The point at which the derivative (slope) is to be taken.
     * @param parameters  This is a set of numbers representing the constants in the above set
     *                    of equations.
     * @return  A Function that returns a derivative based on (1) the differential equations
     * that define this system and (2) the parameters given, which are baked into the function
     * as constants.
     */
    Function<Vector<Double>, Vector<Double>> takeDerivative(Vector<Double> point,
                                                            Vector<Double> parameters);
}
