package TravelSimulation;

import desmoj.core.dist.ContDistExponential;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimProcess;
import desmoj.core.simulator.TimeSpan;

/**
 * Created by András on 4/26/2015.
 *
 */
public class City extends SimProcess {

    public City(Model owner, String name, boolean showInTrace, double visitorArrivalTime) {
        super(owner, name, showInTrace);
        visitorArrivalDistribution = new ContDistExponential(owner, "VisitorArrivalTimeStream", visitorArrivalTime * 100, true, false);
        visitorGenerator = new VisitorGenerator(owner, "VisitorGenerator for " + name, false, this);
    }

    private VisitorGenerator visitorGenerator;
    private ContDistExponential visitorArrivalDistribution;

    public double getVisitorArrivalTime() {
        return visitorArrivalDistribution.sample();
    }

    public void schedule() {
        visitorGenerator.schedule(new TimeSpan(getVisitorArrivalTime()));
    }

    public void lifeCycle() {

    }

}
