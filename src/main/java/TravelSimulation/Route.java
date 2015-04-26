package TravelSimulation;

import desmoj.core.simulator.ProcessQueue;

/**
 * Created by András on 4/26/2015.
 *
 */
public class Route {

    public Route(City endpoint, double travelTime, double cost) {
        this.endpoint = endpoint;
        this.travelTime = travelTime;
        this.cost = cost;
        touristsWaiting = new ProcessQueue<Tourist>(endpoint.getModel(), "Tourist Waiting Queue", true, false);
    }

    City endpoint;
    double travelTime;
    double cost;

    protected ProcessQueue<Tourist> touristsWaiting;

    public City getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(City endpoint) {
        this.endpoint = endpoint;
    }

    public double getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(double travelTime) {
        this.travelTime = travelTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
