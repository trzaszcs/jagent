package pl.poznan.jagent;


public class ByteArrayClassLoader extends ClassLoader {

    public Class findClass(String name, byte[] ba) {
        return defineClass(name,ba,0,ba.length);
    }

}