package algorithm.nn.framework.component;

import algorithm.nn.framework.NeuralNetworkContext;

/**
 * @author created by zzz at 2019/11/22 15:53
 */

public abstract class BaseNeuralNetworkComponent {

    protected NeuralNetworkContext context;

    public BaseNeuralNetworkComponent(NeuralNetworkContext context) {
        this.context = context;
    }
}
