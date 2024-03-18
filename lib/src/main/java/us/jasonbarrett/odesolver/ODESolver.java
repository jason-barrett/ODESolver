package us.jasonbarrett.odesolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import us.jasonbarrett.odesolver.system.ODESystem;
import us.jasonbarrett.odesolver.method.ODEMethod;

public interface ODESolver {
    public List<Vector<Double>> solve(ODESystem system, ODEMethod method,
                                      Vector<Double> initialCond, Vector<Double> parameters,
                                      double initialTimeStep, double endTime);
}
