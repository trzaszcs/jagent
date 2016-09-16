package pl.poznan.jagent.registry.jmx;




import pl.poznan.jagent.registry.jmx.mbean.JagentStats;
import pl.poznan.jagent.registry.jmx.mbean.JagentStatsMXBean;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class Registry {

    public static final JagentStatsMXBean JAGENT_STATS_MX_BEAN = new JagentStats();

    public static void init() throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("pl.poznan.jagent:type=JagentStats");
        mbs.registerMBean(JAGENT_STATS_MX_BEAN, name);
    }

}
