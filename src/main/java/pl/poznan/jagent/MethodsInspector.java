package pl.poznan.jagent;


import javassist.CtClass;
import javassist.CtMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MethodsInspector {
    private static Logger logger = LoggerFactory.getLogger(MethodsInspector.class);
    private final String postHookStaticCall;

    public MethodsInspector(String postHookStaticCall) {
        this.postHookStaticCall = postHookStaticCall;
    }

    public void inspect(CtMethod method) throws Exception {
        logger.info("Intercepting method : {}", method.getLongName());
        method.addLocalVariable("time", CtClass.longType);
        method.insertBefore("time = System.currentTimeMillis();");
        method.insertAfter(buildHookInvocation(method.getLongName()));
    }


    private String buildHookInvocation(String methodName) {
        return postHookStaticCall + "(\"" + methodName + "\",$args, System.currentTimeMillis() - time);";
    }
}
