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

    City ravenna;

    public void init() {
        //Városok létrehozása
        ravenna = new City(this, "Ravenna", false, visitorArrivalRavenna * 100);
    }

    public void doInitialSchedules() {
        //Városok látogatóinak ütemezése
        ravenna.schedule();
    }

    protected double visitorArrivalRavenna = 0.5;
    public Map<String, AccessPoint> createParameters() {
        Map<String, AccessPoint> pm = new TreeMap<String, AccessPoint>();
        pm.put("Látogatók érkezése Ravennába _ percenként", new MutableFieldAccessPoint("visitorArrivalRavenna", this));
        return pm;
    }

}
