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

    public static double visitAnAttractivity(Visitor visitor) {

        City city = visitor.getCity();

        //Egy látnivaló kiszemelése
        Attractivity attractivity = city.getAttractivities()
                .get(TravelSimulationModel.randomGenerator.nextInt(city.getAttractivities().size()));

        //Belépőjegy megvétele
        attractivity.getCity().revenue += attractivity.getCost();

        //Látogatás
        visitor.hold(new TimeSpan(attractivity.getVisitTime()));

        return attractivity.getCost();
    }

}
