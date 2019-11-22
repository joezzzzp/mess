package test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author created by zzz at 2019/11/13 18:49
 */

public class Test {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < size; i++) {
            int value = scanner.nextInt();
            set.add(value);
        }
        Integer[] ret = set.toArray(new Integer[0]);
        Arrays.sort(ret);
        for(int i : ret) {
            System.out.println(i);
        }
    }
}
