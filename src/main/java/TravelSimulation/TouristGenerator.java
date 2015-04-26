package TravelSimulation;

import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

/**
 * Created by András on 4/20/2015.
 *
 */
public class TouristGenerator extends ExternalEvent {

    //Hivatkozás az adott városhoz
    private City city;
    private TravelSimulationModel travelSimulationModel;

    public TouristGenerator(Model owner, String name, boolean showInReport, City city) {
        super(owner, name, showInReport);
        this.city = city;
        this.travelSimulationModel = (TravelSimulationModel)owner;
    }

    public void eventRoutine() {

        //Új látogató létrehozása és elhelyezése a városban
        Tourist tourist = new Tourist(getModel(), "Próba János", false, city);
        tourist.setFunds(travelSimulationModel.getFund());
        city.getPopulation().add(tourist);

        //Tegye, amit tennie kell!
        tourist.activate(new TimeSpan(0.0));

        //Önmagát visszahívja
        schedule(new TimeSpan(city.getVisitorArrivalTime()));
    }

}
