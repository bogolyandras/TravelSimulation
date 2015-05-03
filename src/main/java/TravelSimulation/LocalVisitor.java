package TravelSimulation;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

/**
 * Created by András on 4/26/2015.
 *
 */

public class LocalVisitor extends Visitor {

    public LocalVisitor(Model owner, String name, boolean showInTrace, City city) {
        super(owner, name, showInTrace, city);
    }

    public void lifeCycle() {

        while (true) {
            hold(new TimeSpan(10.0*100));
        }

    }

}
