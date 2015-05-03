package TravelSimulation;

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

            //Túristák felvétele a várólistáról
            for(Tourist t : route.touristsWaiting) {
                passengers.add(t);
                //Jegykezelés
                t.setFunds(t.getFunds() - route.getCost());
                travelSimulationModel.transportationRevenue += route.getCost();
            }
            route.touristsWaiting.removeAll();
            travelSimulationModel.transportationRevenueSeries.update(travelSimulationModel.transportationRevenue);


            //Út megtétele
            hold(new TimeSpan(route.getTravelTime()));

            //Utasok kirakása az új városba
            for(Tourist t : passengers) {
                t.getCity().getPopulation().remove(t);
                t.setCity(route.endpoint);
                t.activate();
            }
            travelSimulationModel.transportedPeople += passengers.size();
            passengers.clear();
            travelSimulationModel.transportedPeopleSeries.update(travelSimulationModel.transportedPeople);

            //Visszaút
            hold(new TimeSpan(route.getTravelTime()));

        }

    }

}
