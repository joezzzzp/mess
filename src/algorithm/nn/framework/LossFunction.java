package algorithm.nn.framework;

/**
 * 损失函数
 * @author created by zzz at 2019/11/22 14:32
 */

public interface LossFunction {

    /**
     * 计算损失
     * @param input 输入值
     * @return 计算后结果
     */
    double calLoss(double input);
}
