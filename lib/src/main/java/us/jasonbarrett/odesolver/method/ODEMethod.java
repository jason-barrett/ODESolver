package us.jasonbarrett.odesolver.method;

import us.jasonbarrett.odesolver.StepResult;

import java.util.Vector;
import java.util.function.Function;

public interface ODEMethod {
    StepResult step(Vector<Double> point, Function<Vector<Double>,
            Vector<Double>> system, Double timeStep);
}
