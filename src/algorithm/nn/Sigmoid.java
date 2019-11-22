package algorithm.nn;

import algorithm.nn.framework.ActiveFunction;

/**
 * @author created by zzz at 2019/11/22 14:23
 */

public class Sigmoid implements ActiveFunction {

    /**
     * 常量e
     */
    private static final double E = Math.E;

    /**
     *           1
     * S(X) = ---------
     *             -x
     *        1 + e
     * @param input 输入
     * @return Sigmoid后结果
     */
    @Override
    public double active(double input) {
        return 1 / (1 + Math.pow(E, -input));
    }
}
