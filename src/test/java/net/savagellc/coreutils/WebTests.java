package net.savagellc.coreutils;

import net.savagellc.coreutils.web.WebUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class WebTests {

    @Test
    public void downloadFile() throws IOException {
        WebUtils.downloadFile("https://riotgamespatcher-a.akamaihd.net/releases/macpbe/installer/deploy/League%20of%20Legends%20installer%20PBE.dmg",  new File("out.dmg"));
    }
}
