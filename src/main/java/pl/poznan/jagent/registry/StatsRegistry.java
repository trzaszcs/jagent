package pl.poznan.jagent.registry;


import java.time.OffsetDateTime;
import java.util.List;

public interface StatsRegistry {
    void register(String methodName, List<String> args, List<String> callStack, long durationTime, OffsetDateTime statsBuildTime);
}
