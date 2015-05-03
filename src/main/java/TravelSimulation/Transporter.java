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

    public Transporter(Model owner, String name, boolean showInTrace, Route route, int capacity) {
        super(owner, name, showInTrace);
        this.route = route;
        this.capacity = capacity;
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
            }
            route.touristsWaiting.removeAll();


            //Út megtétele
            hold(new TimeSpan(route.getTravelTime()));

            //Utasok kirakása az új városba
            for(Tourist t : passengers) {
                t.getCity().getPopulation().remove(t);
                t.setCity(route.endpoint);
                t.activate();
            }
            passengers.clear();

            //Visszaút
            hold(new TimeSpan(route.getTravelTime()));

        }

    }

}
