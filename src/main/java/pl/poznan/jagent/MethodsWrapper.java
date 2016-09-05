package pl.poznan.jagent;


import javassist.CtClass;
import javassist.CtMethod;

public class MethodsWrapper {
    private final String postHookStaticCall;

    public MethodsWrapper(String postHookStaticCall) {
        this.postHookStaticCall = postHookStaticCall;
    }

    public void inspect(CtMethod method) throws Exception {
        System.out.println("Intercepting method : " + method.getLongName());
        method.addLocalVariable("time", CtClass.longType);
        method.insertBefore("time = System.currentTimeMillis();");
        method.insertAfter(buildHookInvocation(method.getLongName()));
    }


    private String buildHookInvocation(String methodName) {
        return postHookStaticCall + "(\"" + methodName + "\",$args, System.currentTimeMillis() - time);";
    }
}
