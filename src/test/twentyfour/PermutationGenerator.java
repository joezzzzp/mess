package test.twentyfour;

import java.util.HashSet;
import java.util.Set;

/**
 * @author created by zzz at 2019/11/15 18:28
 */

public class PermutationGenerator {

    private int[] source;

    private int[] result;

    private int[] count;

    private boolean isOrder;

    private boolean isFinish;

    private boolean isInit;

    PermutationGenerator(int[] source, boolean isOrder) {
        this.source = source;
        this.isOrder = isOrder;
    }

    private void checkFinish() {
        for (int i : count) {
            if (i != count.length - 1) {
                return;
            }
        }
        isFinish = true;
    }

    private int[] init() {
        count = new int[source.length];
        result = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            result[i] = source[count[i]];
        }
        if (isOrder) {
            return next();
        }
        return result;
    }

    int[] next() {
        if (!isInit) {
            isInit = true;
            return init();
        }
        if (isFinish) {
            return new int[0];
        }
        boolean shouldAdd = true;
        for (int i = count.length - 1; i > -1 && shouldAdd; i--) {
            count[i]++;
            if (count[i] == source.length) {
                count[i] = 0;
                shouldAdd = true;
                continue;
            }
            shouldAdd = false;
        }
        checkFinish();
        Set<Integer> record = new HashSet<>();
        for (int i = 0; i < result.length; i++) {
            int value = source[count[i]];
            if (isOrder && record.contains(value)) {
                break;
            }
            result[i] = value;
            record.add(value);
        }
        if (isOrder && record.size() != source.length) {
            return next();
        }
        return result;
    }

    boolean hasNext() {
        return !isFinish;
    }
}
