package TravelSimulation;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimProcess;

/**
 * Created by András on 4/25/2015.
 *
 */
public abstract class Visitor extends SimProcess {

    public Visitor(Model owner, String name, boolean showInTrace) {
        super(owner, name, showInTrace);
    }

    public void lifeCycle() {

    }

}
