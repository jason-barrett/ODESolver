package us.jasonbarrett.odesolver;

import java.util.Vector;

/**
 * A StepResult is the return value from taking a step along the trajectory of a dynamical system
 * defined by a set of ODEs.  Along with the next point on the trajectory, sometimes the execution
 * of a step will set the next step size to be something different from the current step size.
 * @param nextPoint The next point along the trajectory of a system of ODEs, resulting from
 *                  taking a step using some method for solving ODEs.
 * @param nextStep The step size to use for the next step along the trajectory.  In some
 *                 methods, this is adaptive and may change based on the current dynamics.
 */
public record StepResult(Vector<Double> nextPoint, Double nextStep) {
}
