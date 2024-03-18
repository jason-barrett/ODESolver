package us.jasonbarrett.odesolver.system;

import java.util.Vector;
import java.util.function.Function;

public interface ODESystem {
    Function<Vector<Double>, Vector<Double>> takeDerivative(Vector<Double> point,
                                                            Vector<Double> parameters);
}
