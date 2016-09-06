package pl.poznan.jagent.hook.jmx.mbean;


import java.beans.ConstructorProperties;
import java.util.Date;
import java.util.List;

public class MethodCallStat implements MethodCallStatMBean {
    private final Date creationDate;
    private final String methodName;
    private final List<String> args;
    private final long executionTime;

    @ConstructorProperties({"creationDate", "methodName", "args", "executionTime"})
    public MethodCallStat(Date creationDate, String methodName, List<String> args, long executionTime) {
        this.creationDate = creationDate;
        this.methodName = methodName;
        this.args = args;
        this.executionTime = executionTime;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getMethodName() {
        return methodName;
    }

    public List<String> getArgs() {
        return args;
    }

    public long getExecutionTime() {
        return executionTime;
    }
}
