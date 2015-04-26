package TravelSimulation;

import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

/**
 * Created by András on 4/20/2015.
 *
 */
public class VisitorGenerator extends ExternalEvent {

    private CityModel myModel;

    public VisitorGenerator(Model owner, String name, boolean showInReport) {
        super(owner, name, showInReport);
        myModel = (CityModel)owner;
    }

    public void eventRoutine() {
        schedule(new TimeSpan(myModel.getVisitorArricalTime()));
    }
}
