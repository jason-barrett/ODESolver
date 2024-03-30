package us.jasonbarrett.odesolver;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import us.jasonbarrett.odesolver.method.ForwardEulerMethod;
import us.jasonbarrett.odesolver.system.SimpleHarmonicSystem;
import us.jasonbarrett.odesolver.utils.MathUtils;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestForwardEulerMethod {

    /*
    We use a simple harmonic system to run step tests for each ODE solver method.  It's handy because we know
    the analytic solution to the SHS, so we can compute it at any time t (the class has a method for doing so).
    From there, it's easily compared to the solution a step of any solver method might take.
     */
    SimpleHarmonicSystem simpleHarmonicSystem;

    /*
    This is the analytic solution to the SHS at time t, given initial conditions and parameters.
     */
    Vector<Double> analyticSolution;

    /*
    The initial state vector.
     */
    Vector<Double> initialCond;

    Vector<Double> parameters;

    double TEST_TOLERANCE = 0.1;

    @BeforeEach
    public void setup() {
        simpleHarmonicSystem = new SimpleHarmonicSystem();

        initialCond = new Vector<>(2);
        initialCond.add(-1.0);
        initialCond.add(-2.0);

        parameters = new Vector<>(2);
        parameters.add(2.0);
        parameters.add(0.5);

        /*
        We will test the method on a single step of h = 0.1.
         */
        double endTime = 0.1;

        analyticSolution = simpleHarmonicSystem.analyticSolution(initialCond, parameters, endTime);
    }

    @Test
    public void testForwardEulerStep() {
        ForwardEulerMethod forwardEulerMethod = new ForwardEulerMethod();

        StepResult result = forwardEulerMethod.step(initialCond,
                simpleHarmonicSystem.takeDerivative(initialCond, parameters),
                0.1);

        Vector<Double> nextPoint = result.nextPoint();

        assertTrue(Math.abs(nextPoint.get(0) - analyticSolution.get(0)) < TEST_TOLERANCE);
        assertTrue(Math.abs(nextPoint.get(1) - analyticSolution.get(1)) < TEST_TOLERANCE);
    }
}
