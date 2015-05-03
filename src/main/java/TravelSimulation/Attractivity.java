package TravelSimulation;

import desmoj.core.dist.ContDistUniform;

/**
 * Created by András on 5/3/2015.
 *
 */

public class Attractivity {

    ContDistUniform visitTimeDistribution;
    public double getVisitTime() {
        return visitTimeDistribution.sample();
    }

    private double cost;
    public double getCost() {
        return cost;
    }

    private City city;
    public City getCity() {
        return city;
    }

    private String name;
    public String getName() {
        return name;
    }

    public Attractivity(String name, City city, double cost, double visitTimeMin, double visitTimeMax) {
        visitTimeDistribution =  new ContDistUniform(city.getModel(), "VisitTimeDistribution",
                visitTimeMin, visitTimeMax, true, false);
        this.cost = cost;
        this.city = city;
        this.name = name;
    }

}
