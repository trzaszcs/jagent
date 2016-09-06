package pl.poznan.jagent.hook.jmx;


import pl.poznan.jagent.hook.jmx.mbean.JagentStats;
import pl.poznan.jagent.hook.jmx.mbean.JagentStatsMXBean;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class Registry {

    static final JagentStatsMXBean JAGENT_STATS_MX_BEAN = new JagentStats();

    public static void init() throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("pl.poznan.jagent:type=JagentStats");
        mbs.registerMBean(JAGENT_STATS_MX_BEAN, name);
    }

}
