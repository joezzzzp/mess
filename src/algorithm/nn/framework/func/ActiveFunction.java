package algorithm.nn.framework.func;

/**
 * 激活函数
 * @author created by zzz at 2019/11/22 14:22
 */

public interface ActiveFunction {

    ActiveFunction SIGMOID = new Sigmoid();

    ActiveFunction RELU = new ReLU();

    /**
     * 应用激活函数获得输出
     * @param input 输入
     * @return 返回的输出
     */
    double active(double input);
}
