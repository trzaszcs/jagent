package pl.poznan.jagent;


import pl.poznan.jagent.hook.jmx.Registry;
import pl.poznan.jagent.jdbc.JdbcClassTransformer;

import java.lang.instrument.Instrumentation;

public class Jagent {
    public static void premain(String args, Instrumentation inst) {
        System.out.println("Agent started");
        try {
            new Registry().init();
        } catch (Exception e) {
            System.err.println("JMX Registry failed: " + e);
        }
        inst.addTransformer(new JdbcClassTransformer());
    }
}
