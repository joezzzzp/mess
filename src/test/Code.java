package test;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Code {

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            char[] chars = line.toCharArray();
            if (chars.length < 2) {
                System.out.println(chars.length);
                continue;
            }
            int l1 = calLength(chars, init(chars, 2));
            int l2 = calLength(chars, init(chars, 3));
            System.out.println(Math.max(l1, l2));
        }
    }

    private static List<int[]> init(char[] chars, int length) {
        List<int[]> ret = new LinkedList<>();
        if (length < 2) {
            return ret;
        }
        int plus = length - 1;
        for (int i = 0; i < chars.length - 1 - plus; i++) {
            if (isHuiWen(chars, i, i + plus)) {
                ret.add(new int[]{i, i + plus});
            }
        }
        return ret;
    }

    private static int calLength(char[] chars, List<int[]> last) {
        if (last.isEmpty()) {
            return 1;
        }
        List<int[]> tempLast = last;
        while (!tempLast.isEmpty()) {
            List<int[]> curr = new LinkedList<>();
            for (int[] item : tempLast) {
                int start = item[0] - 1;
                int end = item[1] + 1;
                if (start >= 0 && end <= chars.length - 1 && isHuiWen(chars, start, end)) {
                    curr.add(new int[]{start, end});
                }
            }
            if (curr.isEmpty()) {
                break;
            }
            tempLast = curr;
        }
        return tempLast.get(0)[1] - tempLast.get(0)[0] + 1;
    }

    private static boolean isHuiWen(char[] chars, int start, int end) {
        if (end - start < 1) {
            return true;
        }
        for (int i = 0; i <= (end - start) / 2; i++) {
            if (chars[start + i] != chars[end - i]) {
                return false;
            }
        }
        return true;
    }
 }
