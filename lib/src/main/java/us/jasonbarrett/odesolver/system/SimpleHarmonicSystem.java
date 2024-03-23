package us.jasonbarrett.odesolver.system;

import java.util.Vector;
import java.util.function.Function;

/*
The simple harmonic system models a mass on the end of an undamped spring, oscillating up and down.  Friction
and gravity are neglected in the 'simple' system.

The state space variables are:

  x - the (vertical) position of the mass on the end of the spring
  v - the velocity of the mass, positive (down) or negative (up)

The parameters of this system are:

  k - the spring constant, a measure of the deformability of the spring
  m - the mass at the end of the spring
 */
public class SimpleHarmonicSystem implements ODESystem{

    @Override
    public Function<Vector<Double>, Vector<Double>> takeDerivative(Vector<Double> point, Vector<Double> parameters) {
        Double k = parameters.get(0);
        Double m = parameters.get(1);

        return (p) -> {
            Vector<Double> nextPoint = new Vector<>(p.size());

            nextPoint.add(p.get(1));  // x' = v
            nextPoint.add(-1 * (k / m) * p.get(0));  // v' = -k/m * x

            return nextPoint;
        };
    }

    /**
     This system can be used for testing and evaluation of solvers because the analytic solution is known.
     @param initialCond The initial conditions (x0, v0) at t = 0.
     @param parameters The parameters (k, m).
     @param t The time at which a solution is wanted.
     */
    public Vector<Double> analyticSolution(Vector<Double> initialCond, Vector<Double> parameters, Double t) {
        assert(initialCond.size() == 2);
        assert(parameters.size() == 2);
        assert( t > 0.0);

        Double k = parameters.get(0);
        Double m = parameters.get(1);

        Double x0 = initialCond.get(0);
        Double v0 = initialCond.get(1);

        double w0 = Math.sqrt(k / m);

        Double x_t = (x0 * Math.cos(w0 * t)) + ((v0 / w0) * Math.sin(w0 * t));
        Double v_t = (-1 * w0 * x0 * Math.sin(w0 * t)) + (w0 * (v0 / w0) * Math.cos(w0 * t));

        Vector<Double> finalResult = new Vector<>(2);
        finalResult.add(x_t);
        finalResult.add(v_t);

        return finalResult;
    }
}
