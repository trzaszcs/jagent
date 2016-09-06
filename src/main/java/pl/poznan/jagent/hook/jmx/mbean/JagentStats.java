package pl.poznan.jagent.hook.jmx.mbean;


import java.util.ArrayList;
import java.util.List;

public class JagentStats implements JagentStatsMXBean {
    private final List<MethodCallStat> stats = new ArrayList<>();

    @Override
    public List<MethodCallStat> getStats() {
        return stats;
    }

    @Override
    public void add(MethodCallStat methodCall) {
        stats.add(methodCall);
    }
}
