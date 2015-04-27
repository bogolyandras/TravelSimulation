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
        touristGenerator = new TouristGenerator(owner, "TouristGenerator for " + name, false, this);
        dataUpdater = new DataUpdater(owner, "Dataupdate for " + name, false, this);
    }

    //Lakosság
    List<Visitor> Population = new ArrayList<>();
    public List<Visitor> getPopulation() {
        return Population;
    }

    //Lakosság rögzítése
    TimeSeries populationSeries = new TimeSeries(getModel(), getName() + "Lakossága",
            new TimeInstant(0), new TimeInstant(TravelSimulationModel.stopTime), true, false);
    public TimeSeries getPopulationSeries() {
        return populationSeries;
    }

    //Bevétel rögzítése
    public double revenue = 0;
    TimeSeries revenueSeries = new TimeSeries(getModel(), getName() + "Bevétele",
            new TimeInstant(0), new TimeInstant(TravelSimulationModel.stopTime), true, false);
    public TimeSeries getRevenueSeries() {
        return revenueSeries;
    }

    //Útvonalak
    List<Route> destinations = new ArrayList<>();
    public List<Route> getDestinations() {
        return destinations;
    }

    //Látogatók érkezése kívülről
    private TouristGenerator touristGenerator;
    private ContDistExponential visitorArrivalDistribution;
    public double getVisitorArrivalTime() {
        return visitorArrivalDistribution.sample();
    }

    private DataUpdater dataUpdater;

    public void schedule() {
        touristGenerator.schedule(new TimeSpan(getVisitorArrivalTime()));
        dataUpdater.schedule(new TimeSpan(0.0));
    }

    public void lifeCycle() {

    }

}
