package pl.poznan.jagent.registry;


import java.util.List;

public interface StatsRegistry {
    void register(String methodName, List<String> args, List<String> callStack, long executionTime);
}
