package pl.poznan.jagent.hook.jmx;


import pl.poznan.jagent.hook.jmx.mbean.MethodCallStat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JmxPostHook {
    public static void hook(String methodName, Object[] args, long executionTime) {
        List<String> argsStr = Arrays.asList(args)
                .stream()
                .map(arg ->
                        arg == null ? null : arg.toString()
                )
                .collect(Collectors.toList());

        Registry.JAGENT_STATS_MX_BEAN.add(new MethodCallStat(new Date(), methodName, argsStr, executionTime));
    }

    public static String getHookNameRef() {
        return JmxPostHook.class.getName() + ".hook";
    }


    static class A {
        void doSmth(){
            System.out.println("--->");
            System.out.println("<---");
            Thread t = Thread.currentThread();
            System.out.println("<---");
        }
    }


    public static void main(String[] args) {

        new JmxPostHook.A().doSmth();
        System.out.println("--->");
    }
}
