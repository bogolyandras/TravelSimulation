package TravelSimulation;

import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

/**
 * Created by András on 4/20/2015.
 *
 */
public class VisitorGenerator extends ExternalEvent {

    //Hivatkozás az adott városhoz
    private City city;

    public VisitorGenerator(Model owner, String name, boolean showInReport, City city) {
        super(owner, name, showInReport);
        this.city = city;
    }

    public void eventRoutine() {



        //Önmagát visszahívja
        schedule(new TimeSpan(city.getVisitorArrivalTime()));
    }

}
