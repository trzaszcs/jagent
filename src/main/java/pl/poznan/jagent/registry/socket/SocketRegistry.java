package pl.poznan.jagent.registry.socket;


import pl.poznan.jagent.registry.StatsRegistry;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class SocketRegistry implements StatsRegistry {

    private final Sender sender;
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public SocketRegistry(String address, int port) throws IOException {
        sender = new Sender(address, port);
    }

    @Override
    public void register(String methodName, List<String> args, List<String> callStack, long durationTime, OffsetDateTime statsTime) throws IOException {
        sender.send(json(methodName, args, callStack, durationTime, formatter.format(statsTime)));
    }

    public void close() throws IOException {
        sender.close();
    }

    private String json(String methodName, List<String> args, List<String> callStack, long durationTime, String statsTimeStr) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"methodName\":").append("\"").append(methodName).append("\",");
        sb.append("\"durationTime\":").append(durationTime).append(",");
        sb.append("\"statsTime\":").append("\"").append(statsTimeStr).append("\"").append(",");
        sb.append("\"args\":").append(jsonList(args)).append(",");
        sb.append("\"callStack\":").append(jsonList(callStack));
        sb.append("}");
        return sb.toString();
    }

    private String jsonList(List<String> args) {
        args = args.stream().map(s -> "\"" + s + "\"").collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(String.join(",", args));
        sb.append("]");
        return sb.toString();
    }

}
