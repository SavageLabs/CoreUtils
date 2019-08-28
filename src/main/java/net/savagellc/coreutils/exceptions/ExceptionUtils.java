package net.savagellc.coreutils.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

    /**
     * Returns the stacktrace as String of a @{@link Throwable}
     * @param e The throwable to convert
     * @return the resulting String
     */
    public static String throwableToString(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return  sw.toString();
    }
}
