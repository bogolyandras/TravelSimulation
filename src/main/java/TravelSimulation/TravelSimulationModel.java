package TravelSimulation;

import desmoj.core.dist.ContDistUniform;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeInstant;
import desmoj.core.statistic.Histogram;
import desmoj.core.statistic.TimeSeries;
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
        super(null, "Utazás Szimulációs Modell", true, true);
    }

    public String description() {
        return "Városszimulációs modell.";
    }

    //4 Hét szimulálása
    public static final double stopTime = 4 * 7 * 24 * 60 * 100;

    public static final Random randomGenerator = new Random();

    //Látogatók vagyonának eloszlása
    private ContDistUniform fundsDistribution;
    public double getFund() {
        return fundsDistribution.sample();
    }

    //Városok
    City ravenna, milano, velence;

    //Tömegközelekdési eszközök
    Transporter ravenna_milano, milano_velence, velence_ravenna;

    //Statisztika a tömegközlekedési bevételről
    public double transportationRevenue;
    TimeSeries transportationRevenueSeries;

    //Statisztika a szállított emberek számáról
    public double transportedPeople;
    TimeSeries transportedPeopleSeries;

    //Statisztika az itt eltöltött időről
    Histogram timeSpentHistogram;

    public void init() {
        //Vagyonok eloszlása
        fundsDistribution = new ContDistUniform(this, "FundsDistribution", fundsMinimum, fundsMaximum, true, false);

        transportationRevenue = 0;
        transportationRevenueSeries = new TimeSeries(this, "Tömegközlekedési bevétel",
                new TimeInstant(0), new TimeInstant(TravelSimulationModel.stopTime), true, false);

        transportedPeople = 0;
        transportedPeopleSeries = new TimeSeries(this, "Szállított emberek",
                new TimeInstant(0), new TimeInstant(TravelSimulationModel.stopTime), true, false);

        timeSpentHistogram = new Histogram(this, "Túrista ciklusidő", 0, 400, 10, true, false);

        //Városok létrehozása
        ravenna = new City(this, "Ravenna", false, visitorArrivalRavenna * 100, 100);
        milano = new City(this, "Milánó", false, visitorArrivalMilano * 100, 200);
        velence = new City(this, "Velence", false, visitorArrivalVelence * 100, 150);

        //Városok közötti útvonalak létrehozása
        Route route1 = new Route(milano, 180 * 100, 15.0);
        Route route2 = new Route(velence, 261 * 100, 25.0);
        Route route3 = new Route(ravenna, 220 * 100, 20.0);
        ravenna.getDestinations().add(route1);
        milano.getDestinations().add(route2);
        velence.getDestinations().add(route3);

        //Városok közötti közlekedési eszközök létrehozása
        ravenna_milano = new Transporter(this, "Ravenna Milánó", false, route1, 10);
        milano_velence = new Transporter(this, "Milánó Velence", false, route2, 20);
        velence_ravenna = new Transporter(this, "Velence Ravenna", false, route3, 30);

        //Városok látnivalói
        ravenna.getAttractivities().add(new Attractivity("Basilica of San Vitae", ravenna, 10, 60 * 100, 120 * 100));
        ravenna.getAttractivities().add(new Attractivity("Mausoleum of Galla Placidia", ravenna, 8, 120 * 100, 240 * 100));

        milano.getAttractivities().add(new Attractivity("Basilica of San Vitae", milano, 10, 60 * 100, 120 * 100));
        milano.getAttractivities().add(new Attractivity("Mausoleum of Galla Placidia", milano, 8, 120 * 100, 240 * 100));

        velence.getAttractivities().add(new Attractivity("Basilica of San Vitae", velence, 10, 60 * 100, 120 * 100));
        velence.getAttractivities().add(new Attractivity("Mausoleum of Galla Placidia", velence, 8, 120 * 100, 240 * 100));
    }

    public void doInitialSchedules() {
        //Városok látogatóinak ütemezése
        ravenna.schedule();
        milano.schedule();
        velence.schedule();

        //Tömegközelekdés indítása
        ravenna_milano.activate();
        milano_velence.activate();
        velence_ravenna.activate();
    }

    //Látogatók érkezésének gyakorisága
    protected double visitorArrivalRavenna = 0.5, visitorArrivalMilano = 0.8, visitorArrivalVelence = 1.0;
    //Látogatók vagyonának összege
    protected double fundsMinimum = 100, fundsMaximum = 1000;

    //A modell eszerint paraméterezhető
    public Map<String, AccessPoint> createParameters() {
        Map<String, AccessPoint> pm = new TreeMap<>();
        pm.put("Látogatók költenivalója _ EUR -tól", new MutableFieldAccessPoint("fundsMinimum", this));
        pm.put("Látogatók költenivalója _ EUR -ig", new MutableFieldAccessPoint("fundsMaximum", this));
        pm.put("Látogatók érkezése Velencébe _ percenként", new MutableFieldAccessPoint("visitorArrivalVelence", this));
        pm.put("Látogatók érkezése Milánóba _ percenként", new MutableFieldAccessPoint("visitorArrivalMilano", this));
        pm.put("Látogatók érkezése Ravennába _ percenként", new MutableFieldAccessPoint("visitorArrivalRavenna", this));
        return pm;
    }

}
