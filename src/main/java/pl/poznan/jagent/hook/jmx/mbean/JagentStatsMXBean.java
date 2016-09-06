package pl.poznan.jagent.hook.jmx.mbean;


import java.util.List;

public interface JagentStatsMXBean {
    List<MethodCallStat> getStats();

    void add(MethodCallStat methodCall);
}
