package TravelSimulation;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeInstant;
import desmoj.core.simulator.TimeOperations;

/**
 * Created by András on 4/26/2015.
 *
 */

public class Tourist extends Visitor {

    public Tourist(Model owner, String name, boolean showInTrace, City city) {
        super(owner, name, showInTrace, city);
        timeArrived = presentTime();
    }

    //region Funds
    private double funds;

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }
    //endregion

    //region TimeSpentTravelling
    private TimeInstant timeArrived;

    public double getTimeSpent() {
        return TimeOperations.diff(timeArrived, presentTime()).getTimeAsDouble() / (100 * 60);
    }
    //endregion

    public void lifeCycle() {

        double decision;
        while (true) {
            decision = TravelSimulationModel.randomGenerator.nextDouble();
            if (decision < 0.1) {
                //10% chance of traveling
                Route route = super.getCity().getDestinations().get(
                        TravelSimulationModel.randomGenerator.nextInt(super.getCity().getDestinations().size()));
                route.touristsWaiting.insert(this);
                passivate(); //Várakozás
            } else {
                //90% chance of speding money
                funds -= visitAnAttractivity(this);
            }

            //If we ran out of money
            if (funds <= 0) break;
        }

        //We go home
        leave();

    }

    public void leave() {
        ((TravelSimulationModel)(this.getCity().getModel())).timeSpentHistogram.update(getTimeSpent());
        super.getCity().getPopulation().remove(this);
    }
}
