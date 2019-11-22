package jvm;

/**
 * @author created by zzz at 2019/11/6 14:59
 */

public class StringTest {

    public static void main(String[] args) {
        String a = "abc";
        String c = new String("abc");
        String b = new String(new char[] {'a', 'b', 'c'});
    }
}
