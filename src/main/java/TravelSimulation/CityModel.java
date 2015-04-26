package TravelSimulation;

import desmoj.core.dist.ContDistExponential;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;
import desmoj.core.util.AccessPoint;
import desmoj.core.util.Parameterizable;
import desmoj.extensions.experimentation.reflect.MutableFieldAccessPoint;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by András on 4/20/2015.
 *
 */
public class CityModel extends Model implements Parameterizable {

    public CityModel() {
        super(null, "VarosModell", true, true);
    }

    public String description() {
        return "Városszimulációs modell.";
    }

    //Látogatók érkezése exponenciális eloszlás szerint
    private ContDistExponential visitorArrivalTime;
    public double getVisitorArricalTime() {
        return visitorArrivalTime.sample();
    }

    public void init() {
        visitorArrivalTime = new ContDistExponential(this, "VisitorArrivalTimeStream", visitorArrival * 100, true, false);
    }

    public void doInitialSchedules() {

        //A látogatók érkezése
        VisitorGenerator visitorGenerator = new VisitorGenerator(this, "VisitorArrival", false);
        visitorGenerator.schedule(new TimeSpan(getVisitorArricalTime()));
    }

    protected double visitorArrival = 0.5;
    public Map<String, AccessPoint> createParameters() {
        Map<String, AccessPoint> pm = new TreeMap<String, AccessPoint>();
        pm.put("Látogatók érkezése _ percenként", new MutableFieldAccessPoint("visitorArrival", this));
        return pm;
    }

}
