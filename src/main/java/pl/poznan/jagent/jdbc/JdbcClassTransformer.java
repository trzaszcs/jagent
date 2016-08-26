package pl.poznan.jagent.jdbc;

import javassist.*;
import pl.poznan.jagent.MethodsInspector;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.sql.Driver;
import java.sql.Statement;


public class JdbcClassTransformer implements ClassFileTransformer {

    private final MethodsInspector methodsInspector = new MethodsInspector();

    public byte[] transform(ClassLoader loader,
                            String className,
                            Class classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer)
            throws IllegalClassFormatException {
        String classNameWithDots = className.replaceAll("\\/", "\\.");
        try {
            ClassPool cp = ClassPool.getDefault();
            cp.insertClassPath(new ByteArrayClassPath(classNameWithDots, classfileBuffer));
            CtClass cc = cp.get(classNameWithDots);

            if (cc.subtypeOf(cp.get(Driver.class.getName()))) {
                methodsInspector.inspect(cc.getDeclaredMethod("connect"));
                return cc.toBytecode();
            }

            if (cc.subtypeOf(cp.get(Statement.class.getName()))) {
                methodsInspector.inspect(cc.getDeclaredMethod("executeQuery"));
                methodsInspector.inspect(cc.getDeclaredMethod("execute"));
                methodsInspector.inspect(cc.getDeclaredMethod("executeUpdate"));
                return cc.toBytecode();
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classfileBuffer;
    }

}
