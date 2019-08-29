package net.savagellc.coreutils.io;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class FileUtils {

    /**
     * Recursively deletes a folder
     * @param folder the folder to iterate
     * @return false if the file is null or the delete failed, otherwise true
     */
    public static boolean deleteFolderRecursive(File folder) {
        if(folder == null) return false;
        if(!folder.isDirectory()) return folder.delete();
        for (File child : folder.listFiles()) {
            if(child.isDirectory()) {
                if(!deleteFolderRecursive(child)) return false;

            }
            if(!child.delete()) return false;
        }
        return true;
    }

    /**
     * read a file as byte array
     * @param f the file to be read
     * @return the resulting byte array
     * @throws Exception
     */
    public static byte[] readFile(File f) throws Exception {
        if(!f.exists()) throw new Exception("File should not be null");
        FileInputStream stream = new FileInputStream(f);
        byte[] result =  IOUtils.streamToByteArray(stream);
        stream.close();
        return result;
    }
    /**
     * read a file as string
     * @param f the file to be read
     * @param encoding the string encoding when converted to chars
     * @return the resulting String
     * @throws Exception
     */
    public static String readFileAsString(File f, String encoding) throws Exception {
        return new String(readFile(f), encoding);
    }
    /**
     * read a file as string with a default charset of UTF-8
     * @param f the file to be read
     * @return the resulting String
     * @throws Exception
     */
    public static String readFileAsString(File f) throws Exception {
        return readFileAsString(f, "UTF-8");
    }

    /**
     * Write or append a byte array to a file
     * @param f the file to write
     * @param data the data to write into the file
     * @param append if it should only append to the file
     * @return currently only true
     * @throws IOException
     */
    public static boolean writeToFile(File f, byte[] data, boolean append) throws IOException {
        FileOutputStream stream = new FileOutputStream(f,append);
        stream.write(data);
        stream.flush();
        stream.close();
        return true;
    }
    /**
     * Write or append a String to a file
     * @param f the file to write
     * @param data the data String to write into the file
     * @param append if it should only append to the file
     * @return currently only true
     * @throws IOException
     */
    public static boolean writeToFile(File f, String data, boolean append) throws IOException {
        return writeToFile(f, data.getBytes(), append);
    }

    /**
     * Pipe a stream to a file
     * @param f the target
     * @param inStream the InputStream to pipe
     * @param append if the file should only be appended to
     * @param bufferSize the buffer size to read
     * @return currently always true
     * @throws IOException
     */
    public static boolean pipeToFile(File f, InputStream inStream, boolean append, int bufferSize) throws IOException {
        FileOutputStream stream = new FileOutputStream(f,append);
        int n;
        byte[] buffer = new byte[bufferSize];
        while((n = inStream.read(buffer)) > -1) {
            stream.write(buffer, 0, n);
        }
        stream.close();
        return true;
    }
    /**
     * Pipe a stream to a file with a default buffer size of 1024
     * @param f the target
     * @param inStream the InputStream to pipe
     * @param append if the file should only be appended to
     * @return currently always true
     * @throws IOException
     */
    public static boolean pipeToFile(File f, InputStream inStream, boolean append) throws IOException {
        return pipeToFile(f, inStream, append, 1024);
    }

    /**
     * Loads a jar into the process
     * @param f the file to load
     * @param parentClassLoader the parent classloader to inject tp
     * @return The new classloader created for the inject
     * @throws MalformedURLException
     */
    public static ClassLoader loadJar(File f, ClassLoader parentClassLoader) throws MalformedURLException {
        ClassLoader childLoader = URLClassLoader.newInstance(new URL[]{ f.toURI().toURL() }, parentClassLoader);
        return childLoader;
    }
    /**
     * Loads a jar into the process with the SystemClassLoader as parent loader
     * @param f the file to load
     * @return The new classloader created for the inject
     * @throws MalformedURLException
     */
    public static ClassLoader loadJar(File f) throws MalformedURLException {
       return loadJar(f, ClassLoader.getSystemClassLoader());
    }

    /**
     * Loads a jar into the process by accessing the System class loader through reflection.
     *
     * @param f the file to load
     * @return The new classloader created for the inject
     * @throws MalformedURLException
     */
    public static ClassLoader loadJarUnsafe(File f) throws MalformedURLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Class<?> clazz = cl.getClass();
        Method method = clazz.getSuperclass().getDeclaredMethod("addURL", new Class[]{URL.class});
        method.setAccessible(true);
        method.invoke(cl, f.toURI().toURL());
        return cl;
    }

}
