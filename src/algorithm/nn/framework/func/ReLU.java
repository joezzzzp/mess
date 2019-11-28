package algorithm.nn.framework.func;

/**
 * @author created by zzz at 2019/11/28 17:56
 */

public class ReLU implements ActiveFunction {
    @Override
    public double active(double input) {
        return Math.max(0, input);
    }
}
