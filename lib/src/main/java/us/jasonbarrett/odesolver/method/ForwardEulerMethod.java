package us.jasonbarrett.odesolver.method;

import us.jasonbarrett.odesolver.StepResult;

import java.util.Vector;
import java.util.function.Function;

/*
The forward Euler method calculates the next point on the trajectory as x + h * x'.  The slope times the step size,
basically.
 */
public class ForwardEulerMethod implements ODEMethod {
    @Override
    public StepResult step(Vector<Double> point, Function<Vector<Double>, Vector<Double>> f, Double timeStep) {
        Vector<Double> nextPoint = new Vector<>(point.size());
        Vector<Double> derivative = f.apply(point);
        for( int i = 0; i < point.size(); i++ ) {
            nextPoint.add(point.get(i) + (timeStep * derivative.get(i)));
        }
        return new StepResult(nextPoint, timeStep);
    }
}
