package TravelSimulation;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimProcess;

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

    private List<Tourist> passangers = new ArrayList<>();

    public void lifeCycle() {



    }

}
