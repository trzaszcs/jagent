package pl.poznan.jagent;


import javassist.CtClass;
import javassist.CtMethod;
import pl.poznan.jagent.PostHook;

public class MethodsInspector {

    public void inspect(CtMethod method) throws Exception {
        System.out.println("Intercepting method :" + method.getName());
        method.addLocalVariable("time", CtClass.longType);
        method.insertBefore("time = System.currentTimeMillis();");
        method.insertAfter(buildHookInvocation(method.getLongName()));
    }


    private String buildHookInvocation(String methodName) {
        return PostHook.class.getName() + ".hook(\"" + methodName + "\",$args, System.currentTimeMillis() - time);";
    }
}
