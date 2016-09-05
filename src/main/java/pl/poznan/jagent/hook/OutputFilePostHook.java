package pl.poznan.jagent.hook;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class OutputFilePostHook {

    private static String outputFileName = "jagent.txt";

    public static synchronized void hook(String methodName, Object[] args, long executionTime) {
        try {
            List<String> lines = new ArrayList<>();
            lines.add("name: " + methodName);
            lines.add("args: (size " + args.length + "):" + argsToString(args));
            lines.add("executionTime: " + executionTime);
            lines.add("threadName: " + Thread.currentThread().getName());
            lines.add("");
            Files.write(Paths.get(outputFileName), lines, UTF_8, APPEND, CREATE);
        } catch (Exception ex) {

        }
    }

    private static String argsToString(Object[] args) {
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            sb.append(arg).append(",");
        }
        return sb.toString();
    }

    public static String getHookNameRef() {
        return OutputFilePostHook.class.getName() + ".hook";
    }
}
