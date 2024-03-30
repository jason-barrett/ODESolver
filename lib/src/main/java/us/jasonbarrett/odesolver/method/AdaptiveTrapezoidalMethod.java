package us.jasonbarrett.odesolver.method;

import us.jasonbarrett.odesolver.StepResult;
import us.jasonbarrett.odesolver.utils.MathUtils;

import java.util.Vector;
import java.util.function.Function;

/*
The adaptive trapezoidal method uses the basic trapezoidal method to calculate the next point in two ways:

  1) Using the current step size, h, in a single step
  2) Taking two steps, each of step size h/2

If the difference is higher than a defined tolerance, the method will reduce the step size.
 */
public class AdaptiveTrapezoidalMethod implements ODEMethod {

    /*
    Adaptive methods take measurements using two methods and change the step size until the difference in the
    methods' measurements drops under a tolerance.
     */
    private final double TOLERANCE = 0.1;

    /*
    The safety margin imposes a limit on how much the step size may change at any given iteration.
     */
    private final double SAFETY_MARGIN = 0.9;


    @Override
    public StepResult step(Vector<Double> point, Function<Vector<Double>, Vector<Double>> f, Double timeStep) {
        ;
        Double stepSize = timeStep;

        TrapezoidalMethod trapezoidalMethod = new TrapezoidalMethod();
        StepResult fullStep = trapezoidalMethod.step(point, f, timeStep);

        StepResult halfStep1 = trapezoidalMethod.step(point, f, timeStep / 2);
        StepResult halfStep2 = trapezoidalMethod.step(halfStep1.nextPoint(), f, timeStep / 2);

        try {
            double error = MathUtils.scaledVectorDistance(fullStep.nextPoint(), halfStep2.nextPoint());

            if( (error / timeStep) < TOLERANCE ) {
                return new StepResult(halfStep2.nextPoint(), timeStep);
            } else {
                stepSize = SAFETY_MARGIN * TOLERANCE * stepSize * stepSize / error;
                return new StepResult(halfStep2.nextPoint(), stepSize);
            }

        } catch(IllegalArgumentException e) {
            return null;
        }
    }

}
