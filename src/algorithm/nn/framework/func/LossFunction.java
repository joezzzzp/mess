package algorithm.nn.framework.func;

/**
 * 损失函数
 * @author created by zzz at 2019/11/22 14:32
 */

public interface LossFunction {

    LossFunction SQUARE_ERROR = new SquareError();

    /**
     * 计算损失
     * @param output 实际输出
     * @param expected 期望输出
     * @return 计算后结果
     */
    double calLoss(double[] output, double[] expected);
}
