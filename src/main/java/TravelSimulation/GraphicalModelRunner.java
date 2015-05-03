package TravelSimulation;

import desmoj.core.statistic.TimeSeries;
import desmoj.core.util.AccessPoint;
import desmoj.core.util.SimRunListener;
import desmoj.extensions.experimentation.ui.ExperimentStarterApplication;
import desmoj.extensions.experimentation.ui.GraphicalObserverContext;
import desmoj.extensions.experimentation.ui.TimeSeriesPlotter;
import desmoj.extensions.experimentation.util.AccessUtil;
import desmoj.extensions.experimentation.util.ExperimentRunner;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by András on 4/20/2015.
 *
 */
public class GraphicalModelRunner extends ExperimentRunner {

    public GraphicalModelRunner() {
        super();
    }

    //Diagramok elhelyezése
    public SimRunListener[] createSimRunListeners(GraphicalObserverContext c) {

        TravelSimulationModel travelSimulationModel = (TravelSimulationModel)getModel();

        TimeSeriesPlotter tp1 = new TimeSeriesPlotter("Városok populációi", c, travelSimulationModel.ravenna.getPopulationSeries(), 400,360, travelSimulationModel.ravenna.getPopulationSeries().getName());
        tp1.addTimeSeries(travelSimulationModel.velence.getPopulationSeries());
        tp1.addTimeSeries(travelSimulationModel.milano.getPopulationSeries());

        TimeSeriesPlotter tp2 = new TimeSeriesPlotter("Városok bevételei", c, travelSimulationModel.ravenna.getRevenueSeries(), 400,360, travelSimulationModel.ravenna.getRevenueSeries().getName());
        tp2.addTimeSeries(travelSimulationModel.velence.getRevenueSeries());
        tp2.addTimeSeries(travelSimulationModel.milano.getRevenueSeries());

        TimeSeriesPlotter tp3 = new TimeSeriesPlotter("Szállított emberek", c, travelSimulationModel.transportedPeopleSeries, 400, 360);

        TimeSeriesPlotter tp4 = new TimeSeriesPlotter("Tömegközlekedés bevételei", c, travelSimulationModel.transportationRevenueSeries, 400, 360);

        tp1.setLocation(0, 0);
        tp1.setSize(500, 300);

        tp2.setLocation(510, 0);
        tp2.setSize(500, 300);

        tp3.setLocation(0, 310);
        tp3.setSize(500, 300);

        tp4.setLocation(510, 310);
        tp4.setSize(500, 300);

        return new SimRunListener[] { tp1, tp2, tp3, tp4 };

    }

    //Alapértelmezett paraméterek átállítása
    public Map<String,AccessPoint> createParameters() {
        Map<String,AccessPoint> pm = super.createParameters();
        AccessUtil.setValue(pm, EXP_STOP_TIME, TravelSimulationModel.stopTime);
        AccessUtil.setValue(pm, EXP_TRACE_STOP, 100.0);
        AccessUtil.setValue(pm, EXP_REF_UNIT, TimeUnit.MINUTES);
        return pm;
    }

    public static void main(String[] args) throws Exception {
        new ExperimentStarterApplication(TravelSimulationModel.class, GraphicalModelRunner.class).setVisible(true);
    }

}
