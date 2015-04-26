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
public class TravelSimulationModel extends Model implements Parameterizable {

    public TravelSimulationModel() {
        super(null, "VarosModell", true, true);
    }

    public String description() {
        return "Városszimulációs modell.";
    }

    public static final double stopTime = 720000.0;

    City ravenna, milano, velence;

    public void init() {
        //Városok létrehozása
        ravenna = new City(this, "Ravenna", false, visitorArrivalRavenna * 100);
        milano = new City(this, "Milánó", false, visitorArrivalMilano * 100);
        velence = new City(this, "Velence", false, visitorArrivalVelence * 100);
    }

    public void doInitialSchedules() {
        //Városok látogatóinak ütemezése
        ravenna.schedule();
        milano.schedule();
        velence.schedule();
    }

    protected double visitorArrivalRavenna = 0.5, visitorArrivalMilano = 0.8, visitorArrivalVelence = 1.0;
    public Map<String, AccessPoint> createParameters() {
        Map<String, AccessPoint> pm = new TreeMap<String, AccessPoint>();
        pm.put("Látogatók érkezése Ravennába _ percenként", new MutableFieldAccessPoint("visitorArrivalRavenna", this));
        pm.put("Látogatók érkezése Milánóba _ percenként", new MutableFieldAccessPoint("visitorArrivalMilano", this));
        pm.put("Látogatók érkezése Velencébe _ percenként", new MutableFieldAccessPoint("visitorArrivalVelence", this));
        return pm;
    }

}
