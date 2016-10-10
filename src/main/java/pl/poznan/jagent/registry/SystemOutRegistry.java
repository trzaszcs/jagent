package pl.poznan.jagent.registry;


import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SystemOutRegistry implements StatsRegistry {

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public void register(String methodName, List<String> args, List<String> callStack, long durationTime, OffsetDateTime statsBuildTime) {
        System.out.println("method call stats");
        System.out.println("name: " + methodName);
        System.out.println("time: " + formatter.format(statsBuildTime));
        System.out.println("args: (size " + args.size() + "):" + argsToString(args));
        System.out.println("executionTime: " + durationTime);
        System.out.println("threadName: " + Thread.currentThread().getName());
    }

    private String argsToString(List<String> args) {
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            sb.append(arg).append(",");
        }
        return sb.toString();
    }
}
