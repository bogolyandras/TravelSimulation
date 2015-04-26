package TravelSimulation;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

/**
 * Created by András on 4/26/2015.
 *
 */

public class Tourist extends Visitor {

    public Tourist(Model owner, String name, boolean showInTrace, City city) {
        super(owner, name, showInTrace, city);
    }

    private double funds;

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    public void lifeCycle() {

        while (true) {
            hold(new TimeSpan(10000.0));
            funds -= 10;
            if (funds <= 0) break;
        }

        leave();

    }

    public void leave() {
        super.getCity().getPopulation().remove(this);
    }
}
