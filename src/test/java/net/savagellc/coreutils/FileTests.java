package net.savagellc.coreutils;

import net.savagellc.coreutils.io.FileUtils;
import org.json.JSONObject;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

public class FileTests {

    @Test
    public void injectJar() throws MalformedURLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        FileUtils.loadJarUnsafe(new File("/Users/liz3/Downloads/json-20190722.jar"));
        System.out.println(new JSONObject().put("test", 123));
    }
}
