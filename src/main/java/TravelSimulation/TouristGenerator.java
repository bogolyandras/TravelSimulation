package TravelSimulation;

import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

/**
 * Created by András on 4/20/2015.
 *
 */
public class TouristGenerator extends ExternalEvent {

    //Reference to the city we are in
    private City city;
    private TravelSimulationModel travelSimulationModel;

    public TouristGenerator(Model owner, String name, boolean showInReport, City city) {
        super(owner, name, showInReport);
        this.city = city;
        this.travelSimulationModel = (TravelSimulationModel)owner;
    }

    public void eventRoutine() {

        //Create and place a new tourist
        Tourist tourist = new Tourist(getModel(), "Sample Tourist", false, city);
        tourist.setFunds(travelSimulationModel.getFund());
        city.getPopulation().add(tourist);

        //Let him/her do what he/she should
        tourist.activate(new TimeSpan(0.0));

        //Then we wait for the next tourists, call back ourselves
        schedule(new TimeSpan(city.getVisitorArrivalTime()));
    }

}
