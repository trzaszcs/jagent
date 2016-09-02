package pl.poznan.jagent.hook;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerPostHook {
    private static Logger logger = LoggerFactory.getLogger(LoggerPostHook.class);

    public static void hook(String methodName, Object[] args, long executionTime) {
        logger.info("method call stats");
        logger.info("name: " + methodName);
        logger.info("args: (size " + args.length + "):" + argsToString(args));
        logger.info("executionTime: " + executionTime);
        logger.info("threadName: " + Thread.currentThread().getName());
    }

    private static String argsToString(Object[] args) {
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            sb.append(arg).append(",");
        }
        return sb.toString();
    }

    public static String getHookNameRef() {
        return LoggerPostHook.class.getName() + ".hook";
    }
}
