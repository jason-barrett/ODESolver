package us.jasonbarrett.odesolver;

import java.util.*;
import us.jasonbarrett.odesolver.method.ODEMethod;
import us.jasonbarrett.odesolver.system.ODESystem;

public class ODESolverImpl implements ODESolver {
    /**
     *
     * @param system A system of ODEs to solve, defined in terms of a function from
     *               a point in that space to the derivative of the system at that
     *               point.
     * @param method The ODE solution method to use (Euler, Runge-Kutta, etc.).  The method's
     *               step() method returns the next point, given the current point, the system,
     *               and the time step.
     * @param initialCond The initial point in the solution space.
     * @param parameters A vector or parameters that are specific to this ODE system.  It is
     *                   a little clumsy that these are passed in separately from the system.
     * @param initialTimeStep The time step to take to the next point.
     * @param endTime  The time (assuming the start time = 0) when the solution ends.
     * @return A trajectory of points along the state space.  This illustrates the
     * dynamics of the ODE system.  In case of any errors, this returns an empty trajectory
     * instead of null.
     */
    public List<Vector<Double>> solve(ODESystem system, ODEMethod method,
                                           Vector<Double> initialCond, Vector<Double> parameters,
                                           double initialTimeStep, double endTime) {

        Objects.requireNonNull(system, "ODESystem cannot be null");
        Objects.requireNonNull(method, "ODEMethod cannot be null");
        Objects.requireNonNull(initialCond, "Initial conditions cannot be null");
        Objects.requireNonNull(parameters, "Parameters cannot be null");

        List<Vector<Double>> trajectory = new ArrayList<>();
        trajectory.add(initialCond);

        Vector<Double> point = new Vector<>(initialCond);
        double t = initialTimeStep;
        double stepSize = initialTimeStep;
        while( t <= endTime ) {
            /*
            Get the next point in the trajectory.
             */
            StepResult stepResult = method.step(point,
                    system.takeDerivative(point, parameters),
                    stepSize);

            Vector<Double> nextPoint = stepResult.nextPoint();
            trajectory.add(nextPoint);
            point = nextPoint;

            /*
            Get the next step size to use and advance time.
             */
            stepSize = stepResult.nextStep();
            t += stepSize;
        }

        return Collections.unmodifiableList(trajectory);
    }

}
