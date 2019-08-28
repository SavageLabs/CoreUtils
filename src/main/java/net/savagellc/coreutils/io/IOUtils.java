package net.savagellc.coreutils.io;

import java.io.*;

public class IOUtils {

    /**
     * Transfers the content bytes of a @{@link InputStream} to a @{@link OutputStream}
     * @param in stream to pipe
     * @param out target stream
     * @param bufferSize the buffer size to read
     * @throws IOException
     */
    public static void pipe(InputStream in, OutputStream out, int bufferSize) throws IOException {
        if(in == null) throw new NullPointerException("InputStream should not be null");
        if(out == null) throw new NullPointerException("OutputStream should not be null");
        if(bufferSize <= 0) throw new IllegalArgumentException("The Buffer size should not be smaller or equal to 0");
        int n;
        byte[] buffer = new byte[bufferSize];
        while((n = in.read(buffer)) > -1) {
            out.write(buffer, 0, n);   // Don't allow any extra bytes to creep in, final write
        }
    }
    /**
     * Transfers the content bytes of a @{@link InputStream} to a @{@link OutputStream} with a default buffer size of 1024
     * @param in stream to pipe
     * @param out target stream
     * @throws IOException
     */
    public static void pipe(InputStream in, OutputStream out) throws IOException {
            pipe(in, out, 1024);
    }

    /**
     * Converts a @{@link InputStream} to a {@link String}
     * @param stream the @{@link InputStream} to convert
     * @param charSet the Charset in which the stream will be read
     * @return the resulting @{@link String}
     * @throws IOException
     */
    public static String convertToString(InputStream stream, String charSet) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(stream, charSet);
        for (; ; ) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }

    /**
     * convert a @{@link InputStream} to a byte array
     * @param stream the stream to convert
     * @return the resulting byte array
     * @throws IOException
     */
    public static byte[] streamToByteArray(InputStream stream) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        pipe(stream, out);
        return out.toByteArray();
    }
    /**
     * Converts a @{@link InputStream} to a {@link String} with a UTF-8 default Charset
     * @param stream the @{@link InputStream} to convert
     * @return the resulting @{@link String}
     * @throws IOException
     */
    public static String convertToString(InputStream stream) throws IOException {
       return convertToString(stream, "UTF-8");
    }
}
