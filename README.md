This is a basic general functional ordinary differential equation (ODE) solver library written in Java.

I had the assignment to write some specific ODE solvers for an online class.  I wanted to take the opportunity to explore some functional programming concepts in Java.

The idea was that the entry point to the solver is the interface ODESolver.  One solves a given ODESystem of differential equations using an ODEMethod.  The library user
can give the ODESolver the ODESystem and ODEMethod and it does the work.  The ODESystem and the ODEMethod can be coded independently of the ODESolver as long as they
adhere to the interface specifications.


The ODESystem is a means of getting the next point in the dynamics defined by this set of ODEs, given the current point.  The solution of the ODEs at this point is the
slope in the state space, along which the next point will be found.  The solver will take a 'step', given that slope, in accordance with the step size it's using.

The system is specified by the function:

    Function<Vector<Double>, Vector<Double>> takeDerivative(Vector<Double> point,
                                                            Vector<Double> parameters);

takeDerivative() takes the current point of evaluation and the parameters of the system, and returns a curried Function which turns a given point in the state space 
into a vector slope towards the next point, with the parameters baked in.

The library is packaged with a SimpleHarmonicSystem for test purposes.  It's handy to test with since the analytic solution is known.


The ODEMethod is a means of taking a step from a point in state space, given the slope defined by the solution to the ODESystem.  The simplest method, ForwardEulerMethod, 
scalar-multiplies a step size by the slope, and there's your next point.  Lots of other methods exist with varying degrees of sophistication and error-correction.  
ForwardEulerMethod and a few other simple methods are also provided as samples with the library.

The method is specified by the function:

    StepResult step(Vector<Double> point, Function<Vector<Double>,
            Vector<Double>> system, Double timeStep);

A StepResult is a record containing a 'next point' and a 'next step size', for methods with adaptive step sizes.

step() is given a current point and a time step, and the dynamics as defined by the Function returned from ODESystem.takeDerivative().  It provides information on the 
next point in the ODE solution trajectory, given the method defined by its implementation.


For illustration, the crux of the implementation of the ODESolver combines the ODESystem and the ODEMethod like so:

            StepResult stepResult = method.step(point,
                                                system.takeDerivative(point, parameters),
                                                stepSize);

Steps are repeated from t=0 to the end time, at which a solution to the system is desired.
