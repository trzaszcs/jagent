package pl.poznan.jagent.hook;


public class SystemOutPostHook {
    public static void hook(String methodName, Object[] args, long executionTime) {
        System.out.println("method call stats");
        System.out.println("name: " + methodName);
        System.out.println("args: (size " + args.length + "):" + argsToString(args));
        System.out.println("executionTime: " + executionTime);
        System.out.println("threadName: " + Thread.currentThread().getName());
    }

    private static String argsToString(Object[] args) {
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            sb.append(arg).append(",");
        }
        return sb.toString();
    }

    public static String getHookNameRef(){
        return SystemOutPostHook.class.getName()+".hook";
    }
}
