package algorithm.nn.framework.func;

import algorithm.nn.framework.func.ActiveFunction;

/**
 * @author created by zzz at 2019/11/22 14:23
 */

public class Sigmoid implements ActiveFunction {

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
        return 1 / (1 + Math.exp(-input));
    }
}
