package pl.poznan.jagent;


import pl.poznan.jagent.hook.PostHook;
import pl.poznan.jagent.registry.FileRegistry;
import pl.poznan.jagent.registry.jmx.JmxRegistry;
import pl.poznan.jagent.registry.jmx.Registry;
import pl.poznan.jagent.jdbc.JdbcClassTransformer;

import java.lang.instrument.Instrumentation;

public class Jagent {
    public static void premain(String args, Instrumentation inst) {
        System.out.println("Agent started");
        PostHook.setStatsRegistry(new FileRegistry());
        inst.addTransformer(new JdbcClassTransformer());
    }

    private void jmx(){
        try {
            new Registry().init();
        } catch (Exception e) {
            System.err.println("JMX Registry failed: " + e);
        }
        PostHook.setStatsRegistry(new JmxRegistry());
    }
}
