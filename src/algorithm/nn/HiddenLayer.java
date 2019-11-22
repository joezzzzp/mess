package algorithm.nn;

import algorithm.nn.framework.BaseLayer;
import algorithm.nn.framework.NeuralNetworkContext;

/**
 * @author created by zzz at 2019/11/22 18:11
 */

public class HiddenLayer extends BaseLayer {

    public HiddenLayer(NeuralNetworkContext context, int neuronNumber) {
        super(context, neuronNumber);
    }

    public HiddenLayer(NeuralNetworkContext context, int neuronNumber, double bias) {
        super(context, neuronNumber, bias);
    }
}
