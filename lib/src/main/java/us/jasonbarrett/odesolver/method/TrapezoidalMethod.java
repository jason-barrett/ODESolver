package us.jasonbarrett.odesolver.method;

import us.jasonbarrett.odesolver.StepResult;

import java.util.Vector;
import java.util.function.Function;

/*
The trapezoidal method calculates the next point on the trajectory by taking a forward Euler step and a backward
Euler step and averaging the two.
 */
public class TrapezoidalMethod implements ODEMethod {
    @Override
    public StepResult step(Vector<Double> point, Function<Vector<Double>, Vector<Double>> f, Double timeStep) {

        /*
        Take a forward Euler step.
         */
        ForwardEulerMethod forwardEulerMethod = new ForwardEulerMethod();
        StepResult result = forwardEulerMethod.step(point, f, timeStep);
        Vector<Double> interimPoint = result.nextPoint();

        /*
        Calculate the slope of the system at the original point and at the candidate next point obtained
        by the forward Euler step.
         */
        Vector<Double> derivative = f.apply(point);
        Vector<Double> nextDerivative = f.apply(interimPoint);

        /*
        The trapezoidal method gets the next point y as:
           y(n + 1) = y(n) + (h / 2) * (f'(y(n)) + f'(y(n + 1)))
         */
        Vector<Double> nextPoint = new Vector<>(point.size());
        for( int i = 0; i < point.size(); i++ ) {
            nextPoint.add(point.get(i) + (timeStep / 2) * (derivative.get(i) + nextDerivative.get(i)));
        }

        return new StepResult(nextPoint, timeStep);
    }
}
