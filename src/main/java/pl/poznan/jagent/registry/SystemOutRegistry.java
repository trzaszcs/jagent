package pl.poznan.jagent.registry;


import java.util.List;

public class SystemOutRegistry implements StatsRegistry {

    @Override
    public void register(String methodName, List<String> args, List<String> callStack, long executionTime) {
        System.out.println("method call stats");
        System.out.println("name: " + methodName);
        System.out.println("args: (size " + args.size() + "):" + argsToString(args));
        System.out.println("executionTime: " + executionTime);
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
