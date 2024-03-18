package us.jasonbarrett.odesolver;

import java.util.Vector;

public record StepResult(Vector<Double> nextPoint, Double nextStep) {
}
