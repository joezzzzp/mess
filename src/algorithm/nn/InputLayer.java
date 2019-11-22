package algorithm.nn;

import algorithm.nn.framework.BaseLayer;
import algorithm.nn.framework.NeuralNetworkContext;

/**
 * @author created by zzz at 2019/11/22 18:06
 */

public class InputLayer extends BaseLayer {

    public InputLayer(NeuralNetworkContext context, int neuronNumber) {
        super(context, neuronNumber);
    }

    public InputLayer(NeuralNetworkContext context, int neuronNumber, double bias) {
        super(context, neuronNumber, bias);
    }

    @Override
    public double[] forward(double[] input) {
        return input;
    }
}
