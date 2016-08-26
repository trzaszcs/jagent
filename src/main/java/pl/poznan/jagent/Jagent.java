package pl.poznan.jagent;


import java.lang.instrument.Instrumentation;

public class Jagent {
    public static void premain(String args, Instrumentation inst) {
        System.out.println("Agent started");
        inst.addTransformer(new SimpleClassTransformer());
    }
}
