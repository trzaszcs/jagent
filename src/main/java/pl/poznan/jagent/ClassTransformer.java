package pl.poznan.jagent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;


public class ClassTransformer implements ClassFileTransformer {
    public byte[] transform(ClassLoader    loader,
                            String              className,
                            Class            classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[]              classfileBuffer)
            throws IllegalClassFormatException {
        System.out.println(className);
        return classfileBuffer;
    }

}
