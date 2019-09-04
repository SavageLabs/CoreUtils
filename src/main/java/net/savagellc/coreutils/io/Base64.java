package net.savagellc.coreutils.io;

public class Base64 {

    /**
     * convert byte array to base64 utf string
     * @param data the data byte array to convert
     * @return the resulting base64 string
     */
    public static String encode(byte[] data) {
        return java.util.Base64.getEncoder().encodeToString(
                data);
    }
    /**
     * convert a string to base64 utf string
     * @param data the data byte array to convert
     * @return the resulting base64 string
     */
    public static String encode(String data) {
        return encode(data.getBytes());
    }

    /**
     * convert a base64 byte array to original string
     * @param data the data byte array to convert
     * @return the resulting original string
     */
    public static String decode(byte[] data) {
        return new String(java.util.Base64.getDecoder().decode(data));
    }
    /**
     * convert a base64 string to the original string
     * @param data the data string to convert
     * @return the resulting original string
     */
    public static String decode(String data) {
        return new String(java.util.Base64.getDecoder().decode(data));
    }
}
