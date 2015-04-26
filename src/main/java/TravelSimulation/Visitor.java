package TravelSimulation;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimProcess;
import desmoj.core.simulator.TimeSpan;

/**
 * Created by András on 4/25/2015.
 *
 */
public abstract class Visitor extends SimProcess {

    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Visitor(Model owner, String name, boolean showInTrace, City city) {
        super(owner, name, showInTrace);
        this.city = city;
    }

    public void lifeCycle() {

        hold(new TimeSpan(10.0));

    }



}
