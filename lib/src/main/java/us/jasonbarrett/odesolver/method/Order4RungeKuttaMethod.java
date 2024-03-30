package us.jasonbarrett.odesolver.method;

import us.jasonbarrett.odesolver.StepResult;

import java.util.Vector;
import java.util.function.Function;

/*
This is *an* Order 4 Runge-Kutta method - the term applies to a family of methods which may use different constants.
This is the algorithm described in Chapter 4 of Burden and Faires, Numerical Analysis, 5th Edition.
 */
public class Order4RungeKuttaMethod implements ODEMethod {
    @Override
    public StepResult step(Vector<Double> point,
                           Function<Vector<Double>, Vector<Double>> f,
                           Double timeStep) {

        Vector<Double> next_point = new Vector<>(point.size());

        Vector<Double> k1 = f.apply(point);

        Vector<Double> k2 = new Vector<>(point.size());
        for( int i = 0; i < point.size(); i++ ) {
            k2.add((k1.get(i) * timeStep * 0.5) + point.getFirst());
        }
        k2 = f.apply(k2);

        Vector<Double> k3 = new Vector<>(point.size());
        for( int i = 0; i < point.size(); i++ ) {
            k3.add((k2.get(i) * timeStep * 0.5) + point.getFirst());
        }
        k3 = f.apply(k3);

        Vector<Double> k4 = new Vector<>(point.size());
        for( int i = 0; i < point.size(); i++ ) {
            k4.add((k3.get(i) * timeStep) + point.getFirst());
        }
        k4 = f.apply(k4);

        for (int i = 0; i < point.size(); i++) {
            next_point.add(point.get(i)
                    + ((timeStep / 6) * (k1.get(i) + (2 * k2.get(i)) + (2 * k3.get(i)) + k4.get(i))));
        }

        return new StepResult(next_point, timeStep);
    }


}
