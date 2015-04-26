package TravelSimulation;

import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

/**
 * Created by András on 4/26/2015.
 *
 */
public class DataUpdate extends ExternalEvent {

    private TravelSimulationModel travelSimulationModel;
    private City city;

    public DataUpdate(Model owner, String name, boolean showInReport, City city) {
        super(owner, name, showInReport);
        this.city = city;
        this.travelSimulationModel = (TravelSimulationModel)owner;
    }

    public void eventRoutine() {
        city.getPopulationSeries().update(city.getPopulation().size());
        schedule(new TimeSpan(100000.0));
    }

}
