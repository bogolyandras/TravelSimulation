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

        double decision;
        while (true) {
            decision = TravelSimulationModel.randomGenerator.nextDouble();
            if (decision < 0.9) {
                //Kisebb semmittevés 90% os eséllyel
                hold(new TimeSpan(5*60*100));
            } else {
                //10% -os eséllyel egy nevezetesség meglátogatása
                visitAnAttractivity(this);
            }

        }

    }

}
