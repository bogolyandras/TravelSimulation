package com.bogolyandras.travelsimulation;

import desmoj.core.dist.ContDistUniform;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeInstant;
import desmoj.core.statistic.Histogram;
import desmoj.core.statistic.TimeSeries;
import desmoj.core.util.AccessPoint;
import desmoj.core.util.Parameterizable;
import desmoj.extensions.experimentation.reflect.MutableFieldAccessPoint;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by András on 4/20/2015.
 *
 */
public class TravelSimulationModel extends Model implements Parameterizable {

    public TravelSimulationModel() {
        super(null, "Travel Simulation Model", true, true);
    }

    public String description() {
        return "Travel Simulation Model";
    }

    //We are about to simulate 4 weeks
    public static final double stopTime = 4 * 7 * 24 * 60 * 100;

    public static final Random randomGenerator = new Random();

    //Distribution of the visitors funds
    private ContDistUniform fundsDistribution;
    public double getFund() {
        return fundsDistribution.sample();
    }

    //Cities
    City ravenna, milan, venice;

    //Transportation tools
    Transporter ravenna_milan, milan_venice, venice_ravenna;

    //Statistics of the revenue of the public transportation
    public double transportationRevenue;
    TimeSeries transportationRevenueSeries;

    //Statistics of the number of transported people
    public double transportedPeople;
    TimeSeries transportedPeopleSeries;

    //Statistics of time spent
    Histogram timeSpentHistogram;

    public void init() {
        //Distributing funds
        fundsDistribution = new ContDistUniform(this, "FundsDistribution", fundsMinimum, fundsMaximum, true, false);

        transportationRevenue = 0;
        transportationRevenueSeries = new TimeSeries(this, "Revenue of public transportation",
                new TimeInstant(0), new TimeInstant(TravelSimulationModel.stopTime), true, false);

        transportedPeople = 0;
        transportedPeopleSeries = new TimeSeries(this, "Transported people",
                new TimeInstant(0), new TimeInstant(TravelSimulationModel.stopTime), true, false);

        timeSpentHistogram = new Histogram(this, "Tourists cycle time", 0, 400, 10, true, false);

        //Creating cities
        ravenna = new City(this, "Ravenna", false, visitorArrivalRavenna * 100, 100);
        milan = new City(this, "Milan", false, visitorArrivalMilan * 100, 200);
        venice = new City(this, "Venice", false, visitorArrivalVenice * 100, 150);

        //Creating routes between cities
        Route route1 = new Route(milan, 180 * 100, 15.0);
        Route route2 = new Route(venice, 261 * 100, 25.0);
        Route route3 = new Route(ravenna, 220 * 100, 20.0);
        ravenna.getDestinations().add(route1);
        milan.getDestinations().add(route2);
        venice.getDestinations().add(route3);

        //Creating transportation vehicles between cities
        ravenna_milan = new Transporter(this, "Ravenna Milan", false, route1, 10);
        milan_venice = new Transporter(this, "Milan Venice", false, route2, 20);
        venice_ravenna = new Transporter(this, "Venice Ravenna", false, route3, 30);

        //Sights of the cities
        ravenna.getAttractivities().add(new Sight("Basilica of San Vitae", ravenna, 10, 60 * 100, 120 * 100));
        ravenna.getAttractivities().add(new Sight("Mausoleum of Galla Placidia", ravenna, 8, 120 * 100, 240 * 100));

        milan.getAttractivities().add(new Sight("Basilica of San Vitae", milan, 10, 60 * 100, 120 * 100));
        milan.getAttractivities().add(new Sight("Mausoleum of Galla Placidia", milan, 8, 120 * 100, 240 * 100));

        venice.getAttractivities().add(new Sight("Basilica of San Vitae", venice, 10, 60 * 100, 120 * 100));
        venice.getAttractivities().add(new Sight("Mausoleum of Galla Placidia", venice, 8, 120 * 100, 240 * 100));
    }

    public void doInitialSchedules() {
        //Scheduling the visitors of the cities
        ravenna.schedule();
        milan.schedule();
        venice.schedule();

        //Scheduling the public transportaton
        ravenna_milan.activate();
        milan_venice.activate();
        venice_ravenna.activate();
    }

    //Setting the frequency of visitor arrical in each city
    protected double visitorArrivalRavenna = 0.5, visitorArrivalMilan = 0.8, visitorArrivalVenice = 1.0;
    //Funds of the visitors (it is a uniform distribution)
    protected double fundsMinimum = 100, fundsMaximum = 1000;

    //Paramters, that can be set in the model
    public Map<String, AccessPoint> createParameters() {
        Map<String, AccessPoint> pm = new TreeMap<>();
        pm.put("Minimum funds of the visitors in EUR", new MutableFieldAccessPoint("fundsMinimum", this));
        pm.put("Maximum funds of the visitors in EUR", new MutableFieldAccessPoint("fundsMaximum", this));
        pm.put("Tourist arrival time to Venice", new MutableFieldAccessPoint("visitorArrivalVenice", this));
        pm.put("Tourist arrival time to Milan", new MutableFieldAccessPoint("visitorArrivalMilan", this));
        pm.put("Tourist arrival time to Ravenna", new MutableFieldAccessPoint("visitorArrivalRavenna", this));
        return pm;
    }

}
