package TravelSimulation;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimProcess;

/**
 * Created by András on 4/25/2015.
 *
 */
public class Visitor extends SimProcess {

    //private CityModel myModel;

    public Visitor(Model owner, String name, boolean showInTrace) {
        super(owner, name, showInTrace);
        //myModel = (CityModel)owner;
    }

    public void lifeCycle() {

    }

}
