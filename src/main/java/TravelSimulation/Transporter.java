package TravelSimulation;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimProcess;

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

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void lifeCycle() {

    }

}
