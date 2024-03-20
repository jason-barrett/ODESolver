package us.jasonbarrett.odesolver.method;

import us.jasonbarrett.odesolver.StepResult;

import java.util.Vector;
import java.util.function.Function;

public interface ODEMethod {
    /**
     * This method takes a step along a trajectory defined by a system of ODEs.  To implement
     * the method 'step()' is to define the method (no pun intended, unfortunate overloading
     * of terms) for solving the ODE.
     *
     * This may be extended in the future to support multistep methods.
     *
     * @param point  The current point in the ODE's state space.  To 'solve' the ODE using
     *               this method is to find the next point in the state space according to the
     *               dynamics defined by the set of ODEs in the system.
     * @param system  This is a Function from some vector of values of state variables to the
     *                vector of derivatives of those variables.  The equations embodied in this
     *                function define the dynamics of the system we are solving with this method.
     * @param timeStep  The system of derivatives capture the evolution of the state space with
     *                  time.  This is the increment of time to take until the next
     *                  evaluation point./
     * @return  A StepResult, which stores the next point on the system trajectory in state space,
     * as well as the next step size to use.  Some methods adapt the step size to the dynamics.
     */
    StepResult step(Vector<Double> point, Function<Vector<Double>,
            Vector<Double>> system, Double timeStep);
}
