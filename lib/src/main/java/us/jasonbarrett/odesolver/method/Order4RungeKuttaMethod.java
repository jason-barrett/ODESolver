package us.jasonbarrett.odesolver.method;

import us.jasonbarrett.odesolver.StepResult;

import java.util.Vector;
import java.util.function.Function;

public class Order4RungeKuttaMethod implements ODEMethod {
    @Override
    public StepResult step(Vector<Double> point,
                           Function<Vector<Double>, Vector<Double>> f,
                           Double timeStep) {

        Vector<Double> next_point = new Vector<>(point.size());

        Vector<Double> k1 = f.apply(point);

        Vector<Double> k2 = new Vector<>(point.size());
        for( int i = 0; i < point.size(); i++ ) {
            k2.add((k1.get(i) * timeStep * 0.5) + point.get(0));
        }
        k2 = f.apply(k2);

        Vector<Double> k3 = new Vector<>(point.size());
        for( int i = 0; i < point.size(); i++ ) {
            k3.add((k2.get(i) * timeStep * 0.5) + point.get(0));
        }
        k3 = f.apply(k3);

        Vector<Double> k4 = new Vector<>(point.size());
        for( int i = 0; i < point.size(); i++ ) {
            k4.add((k3.get(i) * timeStep) + point.get(0));
        }
        k4 = f.apply(k4);

        for (int i = 0; i < point.size(); i++) {
            next_point.add(point.get(i)
                    + ((timeStep / 6) * (k1.get(i) + k2.get(i) + k3.get(i) + k4.get(i))));
        }

        return new StepResult(next_point, timeStep);
    }


}
