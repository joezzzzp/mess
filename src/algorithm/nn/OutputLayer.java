package algorithm.nn;

import algorithm.nn.framework.BaseLayer;
import algorithm.nn.framework.NeuralNetworkContext;

/**
 * @author created by zzz at 2019/11/22 18:12
 */

public class OutputLayer extends BaseLayer {

    public OutputLayer(NeuralNetworkContext context, int neuronNumber) {
        super(context, neuronNumber);
    }

    public OutputLayer(NeuralNetworkContext context, int neuronNumber, double bias) {
        super(context, neuronNumber, bias);
    }
}
