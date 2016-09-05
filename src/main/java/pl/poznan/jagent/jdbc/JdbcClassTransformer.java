package pl.poznan.jagent.jdbc;

import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import pl.poznan.jagent.MethodsWrapper;
import pl.poznan.jagent.hook.OutputFilePostHook;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.sql.Driver;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class JdbcClassTransformer implements ClassFileTransformer {

    private final MethodsWrapper methodsWrapper = new MethodsWrapper(OutputFilePostHook.getHookNameRef());

    private List<CtClass> classesIntercepted = new ArrayList<>();

    public byte[] transform(ClassLoader loader,
                            String className,
                            Class classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer)
            throws IllegalClassFormatException {
        String classNameWithDots = className.replaceAll("\\/", "\\.");
        if (className.startsWith("javassist") || className.startsWith("shadow") || className.startsWith("java") || className.startsWith("sun")) {
            return null;
        }
        try {
            final ClassPool cp = ClassPool.getDefault();
            cp.insertClassPath(new ByteArrayClassPath(classNameWithDots, classfileBuffer));
            CtClass cc = cp.get(classNameWithDots);

            if (!cc.isFrozen() && !cc.isInterface() && !superClassIntercepted(cc)) {
                // inspect java.sql.Driver.connect
                CtClass driverClass = cp.get(Driver.class.getName());
                if (cc.subtypeOf(driverClass)) {
                    inspectMethodsWithName("connect", cc.getDeclaredMethods());
                    classesIntercepted.add(cc);
                    return cc.toBytecode();
                }
                // inspect java.sql.Statement.execute*
                CtClass statementClass = cp.get(Statement.class.getName());
                if (cc.subtypeOf(statementClass)) {
                    System.out.println("inspecting class:" + cc.getName());
                    CtMethod[] methods = cc.getMethods();
                    inspectMethodsWithName("executeQuery", methods);
                    inspectMethodsWithName("execute", methods);
                    inspectMethodsWithName("executeUpdate", methods);
                    classesIntercepted.add(cc);
                    return cc.toBytecode();
                }
            }
        } catch (Exception e) {
            System.out.println("Problem with inspecting" + e);
        }
        return null;
    }

    private void inspectMethodsWithName(String methodName, CtMethod[] methods) {
        for (CtMethod m : methods) {
            if (m.getName().equalsIgnoreCase(methodName)) {
                try {
                    methodsWrapper.inspect(m);
                } catch (Exception e) {
                    System.out.println("Problem with inspecting method" + e);
                }
            }
        }
    }

    private boolean superClassIntercepted(CtClass ctClass) {
        for (CtClass interceptedClass : classesIntercepted) {
            if (ctClass.subclassOf(interceptedClass)) {
                return true;
            }
        }
        return false;
    }

}
