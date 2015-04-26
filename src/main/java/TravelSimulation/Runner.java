package TravelSimulation;

import desmoj.core.util.AccessPoint;
import desmoj.extensions.experimentation.ui.ExperimentStarterApplication;
import desmoj.extensions.experimentation.util.AccessUtil;
import desmoj.extensions.experimentation.util.ExperimentRunner;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by András on 4/20/2015.
 *
 */
public class Runner extends ExperimentRunner {

    public Runner() {
        super();
    }

    public Map<String,AccessPoint> createParameters() {
        Map<String,AccessPoint> pm = super.createParameters();
        AccessUtil.setValue(pm, EXP_STOP_TIME, 720000.0);
        AccessUtil.setValue(pm, EXP_TRACE_STOP, 100.0);
        AccessUtil.setValue(pm, EXP_REF_UNIT, TimeUnit.MINUTES);
        return pm;
    }

    public static void main(String[] args) throws Exception {
        new ExperimentStarterApplication(CityModel.class, Runner.class).setVisible(true);
    }

}
