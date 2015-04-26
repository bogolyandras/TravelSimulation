package TravelSimulation;

/**
 * Created by András on 4/26/2015.
 *
 */
public class Route {

    public Route(City endpoint, double travelTime) {
        this.endpoint = endpoint;
        this.travelTime = travelTime;
    }

    City endpoint;
    double travelTime;

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
}
