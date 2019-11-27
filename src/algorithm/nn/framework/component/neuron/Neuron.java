package algorithm.nn.framework.component.neuron;

import algorithm.nn.framework.NeuralNetworkContext;
import algorithm.nn.framework.NeuralNetworkException;
import algorithm.nn.framework.component.BaseNeuralNetworkComponent;

/**
 * @author created by zzz at 2019/11/22 14:31
 */

public class Neuron extends BaseNeuralNetworkComponent {

    /**
     * 记录上一层所有节点指向自己的权重
     */
    private double[] weights;

    /**
     * 未运用激活函数前的输出
     */
    private double output;

    public Neuron(NeuralNetworkContext context) {
        super(context);
    }

    public Neuron(NeuralNetworkContext context, int lastLayerNeuronNumber) {
        super(context);
        weights = new double[lastLayerNeuronNumber];
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public double[] getWeights() {
        return weights;
    }

    public double getOutput() {
        return output;
    }

    public double outPutWithOutBiasAndActive(double[] inputs) {
        if (inputs.length != weights.length) {
            throw new NeuralNetworkException("Invalid input");
        }
        double result = 0.0;
        for (int i = 0; i < weights.length; i++) {
            result += inputs[i] * weights[i];
        }
        output = result;
        return result;
    }
}
