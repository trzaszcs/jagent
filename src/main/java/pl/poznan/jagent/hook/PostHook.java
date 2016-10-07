package pl.poznan.jagent.hook;


import pl.poznan.jagent.registry.StatsRegistry;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PostHook {

    private static StatsRegistry statsRegistry;

    public static void hook(String methodName, Object[] args, long executionTime) {
        List<String> argsStr = Arrays.asList(args)
                .stream()
                .map(arg ->
                        arg == null ? null : arg.toString()
                )
                .collect(Collectors.toList());
        List<String> callStack = Arrays.asList(Thread.currentThread().getStackTrace())
                .stream()
                .map(stackTraceElement ->
                        stackTraceElement.toString()
                ).collect(Collectors.toList());
        if (statsRegistry != null) {
            statsRegistry.register(methodName, argsStr, callStack, executionTime);
        }
    }

    public static void setStatsRegistry(StatsRegistry statsRegistry) {
        System.out.println("Registering PostHook: " + statsRegistry.getClass().getName());
        PostHook.statsRegistry = statsRegistry;
    }

    public static String getHookNameRef() {
        return PostHook.class.getName() + ".hook";
    }
}
