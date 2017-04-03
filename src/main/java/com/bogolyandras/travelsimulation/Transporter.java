package com.bogolyandras.travelsimulation;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimProcess;
import desmoj.core.simulator.TimeSpan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by András on 4/26/2015.
 *
 */
public class Transporter extends SimProcess {

    Route route;
    int capacity;
    TravelSimulationModel travelSimulationModel;

    public Transporter(Model owner, String name, boolean showInTrace, Route route, int capacity) {
        super(owner, name, showInTrace);
        this.route = route;
        this.capacity = capacity;
        this.travelSimulationModel = (TravelSimulationModel)owner;
    }

    public int getCapacity() {
        return capacity;
    }

    private List<Tourist> passengers = new ArrayList<>();

    public void lifeCycle() {

        while (true) {

            //Pick up tourists from the queue
            for(Tourist t : route.touristsWaiting) {
                passengers.add(t);
                //Buying tickets
                t.setFunds(t.getFunds() - route.getCost());
                travelSimulationModel.transportationRevenue += route.getCost();
            }
            route.touristsWaiting.removeAll();
            travelSimulationModel.transportationRevenueSeries.update(travelSimulationModel.transportationRevenue);


            //Taking the route
            hold(new TimeSpan(route.getTravelTime()));

            //Place the tourists in the new city
            for(Tourist t : passengers) {
                t.getCity().getPopulation().remove(t);
                t.setCity(route.endpoint);
                t.activate();
            }
            travelSimulationModel.transportedPeople += passengers.size();
            passengers.clear();
            travelSimulationModel.transportedPeopleSeries.update(travelSimulationModel.transportedPeople);

            //The route back
            hold(new TimeSpan(route.getTravelTime()));

        }

    }

}
