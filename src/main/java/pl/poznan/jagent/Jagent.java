package pl.poznan.jagent;


import pl.poznan.jagent.hook.PostHook;
import pl.poznan.jagent.jdbc.JdbcClassTransformer;
import pl.poznan.jagent.registry.FileRegistry;
import pl.poznan.jagent.registry.SystemOutRegistry;
import pl.poznan.jagent.registry.jmx.JmxRegistry;
import pl.poznan.jagent.registry.jmx.Registry;
import pl.poznan.jagent.registry.socket.SocketRegistry;

import java.lang.instrument.Instrumentation;

public class Jagent {
    public static void premain(String args, Instrumentation inst) throws Exception {
        System.out.println("Agent started");
        socket();
        inst.addTransformer(new JdbcClassTransformer());
    }

    private static void jmx() {
        try {
            new Registry().init();
        } catch (Exception e) {
            System.err.println("JMX Registry failed: " + e);
        }
        PostHook.setStatsRegistry(new JmxRegistry());
    }

    private static void out() {
        PostHook.setStatsRegistry(new SystemOutRegistry());
    }

    private static void file() {
        PostHook.setStatsRegistry(new FileRegistry());
    }

    private static SocketRegistry socket() throws Exception {
        SocketRegistry socketRegistry = new SocketRegistry("localhost", 9999);
        PostHook.setStatsRegistry(socketRegistry);
        return socketRegistry;
    }
}
