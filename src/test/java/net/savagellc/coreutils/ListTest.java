package net.savagellc.coreutils;

import net.savagellc.coreutils.list.ListUtils;
import org.junit.Test;

import java.util.ArrayList;

public class ListTest {

    @Test
    public void testMap() {
        ArrayList<String> in = new ArrayList<>();
        in.add("ABC");
        in.add("BAC");
        in.add("CBC");
        for (Character c : ListUtils.map(in, type -> {
            return type.charAt(2);
        })) {
            System.out.println(c);
        }
    }

    @Test
    public void testFilter() {
        ArrayList<String> in = new ArrayList<>();
        in.add("ABC");
        in.add("BAC");
        in.add("CBC");
        for (String out : ListUtils.filter(in, type -> {
            return !type.equals("CBC");
        })) {
            System.out.println(out);
        }
    }
    @Test
    public void testForEach() {
        ArrayList<String> in = new ArrayList<>();
        in.add("ABC");
        in.add("BAC");
        in.add("CBC");
        ListUtils.forEach(in, System.out::println);
    }

    @Test
    public void sortTest() {
        ArrayList<String> in = new ArrayList<>();
        in.add("ABSC");
        in.add("BAC");
        in.add("CSSBC");
        ListUtils.sort(in, (a, b) -> {
            return a.length() - b.length();
        });
    }
}
