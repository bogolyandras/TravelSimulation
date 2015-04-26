package TravelSimulation;

import desmoj.core.dist.ContDistExponential;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimProcess;
import desmoj.core.simulator.TimeInstant;
import desmoj.core.simulator.TimeSpan;
import desmoj.core.statistic.TimeSeries;

import java.util.ArrayList;
import java.util.List;

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

    List<Visitor> Population = new ArrayList<>();
    TimeSeries populationSeries = new TimeSeries(getModel(), getName() + "Lakossága",
            new TimeInstant(0), new TimeInstant(TravelSimulationModel.stopTime), true, false);

    public List<Visitor> getPopulation() {
        return Population;
    }

    public TimeSeries getPopulationSeries() {
        return populationSeries;
    }

    //Látogatók érkezése kívülről
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
