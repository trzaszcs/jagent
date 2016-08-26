package pl.poznan.jagent;


import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class MethodsChanger {

    private MethodsInspector methodsInspector = new MethodsInspector();

    public byte[] changeClass(String className, byte[] classfileBuffer) throws Exception {

        String classNameWithDots = className.replaceAll("\\/", "\\.");
        // load class
        ClassPool cp = ClassPool.getDefault();
        cp.insertClassPath(new ByteArrayClassPath(classNameWithDots, classfileBuffer));
        CtClass cc = cp.get(classNameWithDots);
        // go thru methods
        for (CtMethod method : cc.getDeclaredMethods()) {
            methodsInspector.inspect(method);
        }
        return cc.toBytecode();
    }
}
