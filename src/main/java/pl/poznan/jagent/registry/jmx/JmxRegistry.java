package pl.poznan.jagent.registry.jmx;

import pl.poznan.jagent.registry.StatsRegistry;
import pl.poznan.jagent.registry.jmx.mbean.MethodCallStat;

import java.util.Date;
import java.util.List;


public class JmxRegistry implements StatsRegistry {
    @Override
    public void register(String methodName, List<String> args, List<String> callStack, long executionTime) {
        Registry.JAGENT_STATS_MX_BEAN.add(new MethodCallStat(new Date(), methodName, args, executionTime));
    }
}
