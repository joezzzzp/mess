package algorithm.nn.framework.component.layer;

import algorithm.nn.framework.NeuralNetworkContext;

/**
 * @author created by zzz at 2019/11/22 18:06
 */

public class InputLayer extends BaseLayer {

    public InputLayer(NeuralNetworkContext context) {
        super(context);
    }

    public InputLayer(NeuralNetworkContext context, double bias) {
        super(context, bias);
    }

    @Override
    public double[] forward(double[] input) {
        return input;
    }
}
