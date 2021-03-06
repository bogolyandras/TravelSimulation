package com.bogolyandras.travelsimulation;

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

    public City(Model owner, String name, boolean showInTrace, double visitorArrivalTime, int initialPopulation) {
        super(owner, name, showInTrace);
        visitorArrivalDistribution = new ContDistExponential(owner, "VisitorArrivalTimeStream", visitorArrivalTime * 100, true, false);
        touristGenerator = new TouristGenerator(owner, "TouristGenerator for " + name, false, this);
        dataUpdater = new DataUpdater(owner, "Dataupdate for " + name, false, this);

        //Prepare the population
        Population = new ArrayList<>();
        for(int i = 0; i < initialPopulation; i++) {
            Population.add(new LocalVisitor(owner, "A Local person", false, this));
        }
    }

    //Population
    List<Visitor> Population;
    public List<Visitor> getPopulation() {
        return Population;
    }

    //Sights
    List<Sight> attractivities = new ArrayList<>();
    public List<Sight> getAttractivities() {
        return attractivities;
    }

    //Tracking the population
    TimeSeries populationSeries = new TimeSeries(getModel(), getName() + "Population",
            new TimeInstant(0), new TimeInstant(TravelSimulationModel.stopTime), true, false);
    public TimeSeries getPopulationSeries() {
        return populationSeries;
    }

    //Tracking the revenue
    public double revenue = 0;
    TimeSeries revenueSeries = new TimeSeries(getModel(), getName() + "Revenue",
            new TimeInstant(0), new TimeInstant(TravelSimulationModel.stopTime), true, false);
    public TimeSeries getRevenueSeries() {
        return revenueSeries;
    }

    //Routes
    List<Route> destinations = new ArrayList<>();
    public List<Route> getDestinations() {
        return destinations;
    }

    //Arrival of tourists
    private TouristGenerator touristGenerator;
    private ContDistExponential visitorArrivalDistribution;
    public double getVisitorArrivalTime() {
        return visitorArrivalDistribution.sample();
    }

    private DataUpdater dataUpdater;

    public void schedule() {
        touristGenerator.schedule(new TimeSpan(getVisitorArrivalTime()));
        dataUpdater.schedule(new TimeSpan(0.0));
        for(Visitor v : Population) {
            if (v instanceof LocalVisitor) {
                v.activate();
            }
        }
    }

    public void lifeCycle() {

    }

}
