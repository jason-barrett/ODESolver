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

This implementation is based on ideas in https://personal.math.ubc.ca/~feldman/math/vble.pdf.
 */
public class AdaptiveTrapezoidalMethod implements ODEMethod {

    /*
    Adaptive methods take measurements using two methods and change the step size based on the error rate relative
    to a given tolerance.
     */
    private final double TOLERANCE = 0.1;

    /*
    The safety margin imposes a limit on how much the step size may change at any given iteration.

    It would be good to have a benchmark set of systems with which we might fine-tune these parameters.
     */
    private final double SAFETY_MARGIN = 0.9;


    @Override
    public StepResult step(Vector<Double> point, Function<Vector<Double>, Vector<Double>> f, Double timeStep) {

        Double stepSize = timeStep;

        TrapezoidalMethod trapezoidalMethod = new TrapezoidalMethod();
        StepResult fullStep = trapezoidalMethod.step(point, f, timeStep);

        StepResult halfStep1 = trapezoidalMethod.step(point, f, timeStep / 2);
        StepResult halfStep2 = trapezoidalMethod.step(halfStep1.nextPoint(), f, timeStep / 2);

        try {
            double error = MathUtils.vectorDistance(fullStep.nextPoint(), halfStep2.nextPoint());
            double errorRate = error / stepSize;

            /*
            This will tend to shorten the step size when the error is high, meaning the dynamics are highly
            variable.  It will lengthen the step size when the error is low, meaning the dynamics are
            relatively flat.
             */
            stepSize = SAFETY_MARGIN * (TOLERANCE  / errorRate) * stepSize;

            return new StepResult(halfStep2.nextPoint(), stepSize);

        } catch(IllegalArgumentException e) {
            return null;
        }
    }

}
