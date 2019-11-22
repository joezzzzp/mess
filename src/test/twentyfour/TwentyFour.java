package test.twentyfour;

import java.util.*;

/**
 * @author created by zzz at 2019/11/15 11:09
 */

public class TwentyFour {

    private static final int PLUS = -1;

    private static final int MINUS = -2;

    private static final int MULTIPLE = -11;

    private static final int DIVIDE = -12;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            List<Integer> input = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                input.add(scanner.nextInt());
            }
            solve(input);
        }
    }

    private static void solve(List<Integer> list) {

    }

    private static boolean cal(List<Integer> list) {
        if (list.size() == 1) {
            return list.get(0) == 24;
        }
        for (int i = 0; i < list.size(); i++) {
            int temp = list.get(i);

        }
        return false;
    }
}
