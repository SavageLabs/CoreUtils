package net.savagellc.coreutils.io;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    /**
     * Digests the SHA256 hash of a given byte array
     *
     * @param data the data byte array to hash
     * @return the resulting string
     * @throws NoSuchAlgorithmException
     */
    public static String sha256(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = mDigest.digest(data);
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * Digests the SHA256 hash of a given String
     * @param data the data String to hash
     * @return the resulting string
     * @throws NoSuchAlgorithmException
     */
    public static String sha256(String data) throws NoSuchAlgorithmException {
        return sha256(data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Digests the SHA1 hash of a given byte array
     *
     * @param data the data byte array to hash
     * @return the resulting string
     * @throws NoSuchAlgorithmException
     */
    public static String sha1(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] result = digest.digest(data);
        return String.format("%040x", new BigInteger(1, result));
    }
    /**
     * Digests the SHA1 hash of a given string
     *
     * @param data the data byte array to hash
     * @return the resulting string
     * @throws NoSuchAlgorithmException
     */
    public static String sha1(String data) throws NoSuchAlgorithmException {
        return sha1(data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Digests the SHA512 hash of a given String
     * @param data the data String to hash
     * @return the resulting string
     * @throws NoSuchAlgorithmException
     */
    public static String sha512(String data) throws NoSuchAlgorithmException {
        return sha512(data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Digests the SHA512 hash of a given byte array
     *
     * @param data the data byte array to hash
     * @return the resulting string
     * @throws NoSuchAlgorithmException
     */
    public static String sha512(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] bytes = md.digest(data);
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    /**
     * Digests the MD5 hash of a given byte array
     *
     * @param data the data byte array to hash
     * @return the resulting string
     * @throws NoSuchAlgorithmException
     */
    public static String md5(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] result = md.digest(data);
        StringBuffer sb = new StringBuffer();
        for (byte b : result) {
            sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString();
    }
    /**
     * Digests the MD5 hash of a given String
     * @param data the data String to hash
     * @return the resulting string
     * @throws NoSuchAlgorithmException
     */
    public static String md5(String data) throws NoSuchAlgorithmException {
        return md5(data.getBytes(StandardCharsets.UTF_8));
    }
}
