package pl.poznan.jagent.registry.socket;


import pl.poznan.jagent.registry.StatsRegistry;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SocketRegistry implements StatsRegistry {

    private final Sender sender;

    public SocketRegistry(String address, int port) throws IOException {
        sender = new Sender(address, port);
    }

    @Override
    public void register(String methodName, List<String> args, List<String> callStack, long executionTime) {
        sender.send(json(methodName, args, callStack, executionTime));
    }

    private String json(String methodName, List<String> args, List<String> callStack, long executionTime) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"methodName\":").append("\"").append(methodName).append("\",");
        sb.append("\"executionTime\":").append(executionTime).append(",");
        sb.append("\"args\":").append(jsonList(args)).append(",");
        sb.append("\"callStack\":").append(jsonList(callStack)).append(",");
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
