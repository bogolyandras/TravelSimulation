package TravelSimulation;

import desmoj.core.simulator.Model;

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
            if (decision < 0.1) {
                //Utazás 10% eséllyel
                Route route = super.getCity().getDestinations().get(
                        TravelSimulationModel.randomGenerator.nextInt(super.getCity().getDestinations().size()));
                route.touristsWaiting.insert(this);
                passivate(); //Várakozás
            } else {
                //Pénzköltés 90% eséllyel
                funds -= visitAnAttractivity(this);
            }

            //Ha elfogyott a pénz, akkor hazamegyünk
            if (funds <= 0) break;
        }

        //Hazamegyünk
        leave();

    }

    public void leave() {
        super.getCity().getPopulation().remove(this);
    }
}
