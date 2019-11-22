package test;

import java.util.*;

/**
 * @author created by zzz at 2019/11/13 16:34
 */

public class Main {

    public static void main(String[] args) {
        int[][] input = new int[9][9];
        List<Point> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            String line = scanner.nextLine();
            String[] ss = line.split(" ");
            for(int j = 0; j < 9; j++) {
                int value = Integer.parseInt(ss[j].trim());
                input[i][j] = value;
                if (value == 0) {
                    list.add(new Point(i, j));
                }
            }
        }
        int pace = 0;
        while (pace < list.size()) {
            Point curr = list.get(pace);
            List<Integer> avail = findAvailable(input, curr);
            if(!avail.isEmpty()) {
                int used = avail.get(0);
                input[curr.x][curr.y] = used;
                curr.record.add(used);
                pace++;
            } else {
                curr.record.clear();
                input[curr.x][curr.y] = 0;
                pace--;
            }
        }
        print(input);
    }

    private static List<Integer> findAvailable(int[][] input, Point curr) {
        int[] mark = new int[10];
        for(int i = 0; i < 9; i++) {
            mark[input[curr.x][i]] = 1;
            mark[input[i][curr.y]] = 1;
        }

        int x = curr.x / 3 * 3;
        int y = curr.y / 3 * 3;

        int[][] square = new int[3][3];
        System.arraycopy(input[x], y, square[0], 0, 3);
        System.arraycopy(input[x + 1], y, square[1], 0, 3);
        System.arraycopy(input[x + 2], y, square[2], 0, 3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mark[square[i][j]] = 1;
            }
        }

        List<Integer> ret = new ArrayList<>();
        for (int i = 1; i < mark.length; i++) {
            if(mark[i] == 0 && !curr.record.contains(i)) {
                ret.add(i);
            }
        }
        return ret;
    }

    private static void print(int[][] matrix) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                System.out.print(matrix[x][y]);
                if (y < 8) {
                    System.out.print(" ");
                }
            }
            if (x < 8) {
                System.out.println();
            }
        }
    }

    private static class Point {
        int x;
        int y;
        List<Integer> record = new ArrayList<>();

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
