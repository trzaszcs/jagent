package pl.poznan.jagent.sample;


import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import pl.poznan.jagent.MethodsWrapper;
import pl.poznan.jagent.hook.SystemOutPostHook;

public class MethodsChanger {

    private MethodsWrapper methodsWrapper = new MethodsWrapper(SystemOutPostHook.getHookNameRef());

    public byte[] changeClass(String className, byte[] classfileBuffer) throws Exception {

        String classNameWithDots = className.replaceAll("\\/", "\\.");
        // load class
        ClassPool cp = ClassPool.getDefault();
        cp.insertClassPath(new ByteArrayClassPath(classNameWithDots, classfileBuffer));
        CtClass cc = cp.get(classNameWithDots);
        // go thru methods
        for (CtMethod method : cc.getDeclaredMethods()) {
            methodsWrapper.inspect(method);
        }
        return cc.toBytecode();
    }
}
