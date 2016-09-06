package pl.poznan.jagent.hook.jmx.mbean;


import java.util.Date;
import java.util.List;

public interface MethodCallStatMBean {
    Date getCreationDate();
    String getMethodName();
    List<String> getArgs();
    long getExecutionTime();
}
