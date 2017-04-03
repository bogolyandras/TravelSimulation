package com.bogolyandras.travelsimulation;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimProcess;
import desmoj.core.simulator.TimeSpan;

/**
 * Created by András on 4/25/2015.
 *
 */
public abstract class Visitor extends SimProcess {

    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Visitor(Model owner, String name, boolean showInTrace, City city) {
        super(owner, name, showInTrace);
        this.city = city;
    }

    public static double visitAnAttractivity(Visitor visitor) {

        City city = visitor.getCity();

        //Choose a sight
        Sight sight = city.getAttractivities()
                .get(TravelSimulationModel.randomGenerator.nextInt(city.getAttractivities().size()));

        //buy the ticket
        sight.getCity().revenue += sight.getCost();

        //and visit it
        visitor.hold(new TimeSpan(sight.getVisitTime()));

        return sight.getCost();
    }

}
