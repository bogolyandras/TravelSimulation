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

        double decision;
        while (true) {
            decision = TravelSimulationModel.randomGenerator.nextDouble();
            if (false) {
                //Utazás 10%
                Route route = super.getCity().getDestinations().get(
                        TravelSimulationModel.randomGenerator.nextInt(super.getCity().getDestinations().size()));
                //route.touristsWaiting.insert(this);
                passivate(); //Várakozás
            } else {
                //Pénzköltés 90%
                funds -= 10;
                hold(new TimeSpan(10000.0));
            }

            //Ha elfogyott a pénz, akkor hazamegyünk
            if (funds <= 0) break;
        }

        leave();

    }

    public void leave() {
        super.getCity().getPopulation().remove(this);
    }
}
