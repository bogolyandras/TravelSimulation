package TravelSimulation;

import desmoj.core.simulator.Model;

/**
 * Created by András on 4/26/2015.
 *
 */

public class LocalVisitor extends Visitor {

    public LocalVisitor(Model owner, String name, boolean showInTrace, City city) {
        super(owner, name, showInTrace, city);
    }

}
