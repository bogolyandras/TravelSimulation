package com.bogolyandras.travelsimulation;

import desmoj.core.util.AccessPoint;
import desmoj.core.util.SimRunListener;
import desmoj.extensions.experimentation.ui.ExperimentStarterApplication;
import desmoj.extensions.experimentation.ui.GraphicalObserverContext;
import desmoj.extensions.experimentation.ui.HistogramPlotter;
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

    //Placing diagrams
    public SimRunListener[] createSimRunListeners(GraphicalObserverContext c) {

        TravelSimulationModel travelSimulationModel = (TravelSimulationModel)getModel();

        TimeSeriesPlotter tp1 = new TimeSeriesPlotter("Population of cities", c, travelSimulationModel.ravenna.getPopulationSeries(), 400,360, travelSimulationModel.ravenna.getPopulationSeries().getName());
        tp1.addTimeSeries(travelSimulationModel.venice.getPopulationSeries());
        tp1.addTimeSeries(travelSimulationModel.milan.getPopulationSeries());

        TimeSeriesPlotter tp2 = new TimeSeriesPlotter("Revenue of cities", c, travelSimulationModel.ravenna.getRevenueSeries(), 400,360, travelSimulationModel.ravenna.getRevenueSeries().getName());
        tp2.addTimeSeries(travelSimulationModel.venice.getRevenueSeries());
        tp2.addTimeSeries(travelSimulationModel.milan.getRevenueSeries());

        TimeSeriesPlotter tp3 = new TimeSeriesPlotter("Transported people", c, travelSimulationModel.transportedPeopleSeries, 400, 360);

        TimeSeriesPlotter tp4 = new TimeSeriesPlotter("Revenue of the public transportation", c, travelSimulationModel.transportationRevenueSeries, 400, 360);

        HistogramPlotter hp = new HistogramPlotter("Cycle time of tourists", c, travelSimulationModel.timeSpentHistogram, "Spent hours", 360,360, 365,0);

        tp1.setLocation(0, 0);
        tp1.setSize(500, 300);

        tp2.setLocation(510, 0);
        tp2.setSize(500, 300);

        tp3.setLocation(0, 310);
        tp3.setSize(500, 300);

        tp4.setLocation(510, 310);
        tp4.setSize(500, 300);

        hp.setLocation(1020, 0);
        hp.setSize(400, 610);

        return new SimRunListener[] { tp1, tp2, tp3, tp4, hp };

    }

    //Overriding default parameters
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
