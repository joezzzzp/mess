package algorithm.nn.framework;

/**
 * @author created by zzz at 2019/11/26 10:23
 */

public class SquareError implements LossFunction{

    @Override
    public double calLoss(double[] output, double[] expected) {
        double result = 0;
        for (int i = 0; i < output.length; i++) {
            result += Math.pow((expected[i] - output[i]), 2) / 2;
        }
        return result;
    }
}
