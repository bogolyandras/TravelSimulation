package TravelSimulation;

import desmoj.core.dist.ContDistExponential;
import desmoj.core.dist.ContDistUniform;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;
import desmoj.core.util.AccessPoint;
import desmoj.core.util.Parameterizable;
import desmoj.extensions.experimentation.reflect.MutableFieldAccessPoint;

import java.util.Map;
import java.util.Random;
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

    //4 Hét szimulálása
    public static final double stopTime = 4032000.0;

    public static final Random randomGenerator = new Random();

    private ContDistUniform fundsDistribution;
    public double getFund() {
        return fundsDistribution.sample();
    }

    City ravenna, milano, velence;

    public void init() {
        //Vagyonok eloszlása
        fundsDistribution = new ContDistUniform(this, "FundsDistribution", fundsMinimum, fundsMaximum, true, false);

        //Városok létrehozása
        ravenna = new City(this, "Ravenna", false, visitorArrivalRavenna * 100);
        milano = new City(this, "Milánó", false, visitorArrivalMilano * 100);
        velence = new City(this, "Velence", false, visitorArrivalVelence * 100);

        //Városok közötti útvonalak létrehozása
        ravenna.getDestinations().add(new Route(milano, 180 * 100, 15.0));
        milano.getDestinations().add(new Route(velence, 261 * 100, 25.0));
        velence.getDestinations().add(new Route(ravenna, 220 * 100, 20.0));
    }

    public void doInitialSchedules() {
        //Városok látogatóinak ütemezése
        ravenna.schedule();
        milano.schedule();
        velence.schedule();
    }

    protected double visitorArrivalRavenna = 0.5, visitorArrivalMilano = 0.8, visitorArrivalVelence = 1.0;
    protected double fundsMinimum = 100, fundsMaximum = 1000;
    public Map<String, AccessPoint> createParameters() {
        Map<String, AccessPoint> pm = new TreeMap<String, AccessPoint>();
        pm.put("Látogatók költenivalója _ EUR -tól", new MutableFieldAccessPoint("fundsMinimum", this));
        pm.put("Látogatók költenivalója _ EUR -ig", new MutableFieldAccessPoint("fundsMaximum", this));
        pm.put("Látogatók érkezése Velencébe _ percenként", new MutableFieldAccessPoint("visitorArrivalVelence", this));
        pm.put("Látogatók érkezése Milánóba _ percenként", new MutableFieldAccessPoint("visitorArrivalMilano", this));
        pm.put("Látogatók érkezése Ravennába _ percenként", new MutableFieldAccessPoint("visitorArrivalRavenna", this));
        return pm;
    }

}
