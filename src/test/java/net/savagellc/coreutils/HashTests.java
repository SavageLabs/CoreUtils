package net.savagellc.coreutils;

import net.savagellc.coreutils.io.HashUtils;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

public class HashTests {

    @Test
    public void sha256() throws NoSuchAlgorithmException {
        System.out.println(HashUtils.sha256("12345".getBytes()));
    }
    @Test
    public void sha512() throws NoSuchAlgorithmException {
        System.out.println(HashUtils.sha512("12345".getBytes()));
    }

    @Test
    public void sha1() throws NoSuchAlgorithmException {
        System.out.println(HashUtils.md5("1234"));

    }

    @Test
    public void md5() throws NoSuchAlgorithmException {
        System.out.println(HashUtils.md5("Test"));
    }
}
