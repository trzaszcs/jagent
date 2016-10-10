package pl.poznan.jagent.sample;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;


public class SimpleClassTransformer implements ClassFileTransformer {

    private final MethodsChanger methodsChanger = new MethodsChanger();

    public byte[] transform(ClassLoader loader,
                            String className,
                            Class classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer)
            throws IllegalClassFormatException {

        if (!isJavaOrSun(className)) {
            try {
                return methodsChanger.changeClass(className, classfileBuffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return classfileBuffer;
    }

    private boolean isJavaOrSun(String className) {
        return className.startsWith("java") || className.startsWith("sun") || className.startsWith("jdk");
    }

}
