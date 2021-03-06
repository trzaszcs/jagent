package pl.poznan.jagent.registry;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class FileRegistry implements StatsRegistry {

    private final String outputFileName = "jagent.txt";
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @Override
    public void register(String methodName, List<String> args, List<String> callStack, long durationTime, OffsetDateTime statsBuildTime) {
        try {
            List<String> lines = new ArrayList<>();
            lines.add("name: " + methodName);
            lines.add("args:" + argsToString(args));
            lines.add("statsTime:" + formatter.format(statsBuildTime));
            lines.add("durationTime: " + durationTime);
            lines.add("threadName: " + Thread.currentThread().getName());
            lines.add("");
            Files.write(Paths.get(outputFileName), lines, UTF_8, APPEND, CREATE);
        } catch (Exception ex) {

        }
    }

    private String argsToString(List<String> args) {
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            sb.append(arg).append(",");
        }
        return sb.toString();
    }
}
