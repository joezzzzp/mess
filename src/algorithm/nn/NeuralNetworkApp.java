package algorithm.nn;

import algorithm.nn.framework.NeuralNetworkContext;

/**
 * @author created by zzz at 2019/11/22 18:31
 */

public class NeuralNetworkApp {

    public static void main(String[] args) {
        NeuralNetworkContext context = new NeuralNetworkContext();
        context.setActiveFunction(new Sigmoid());
        NeuralNetwork nn = new NeuralNetwork(context);
        nn.setInputLayer(new InputLayer(context, 2));
        nn.addHiddenLayer(new HiddenLayer(context, 2));
        nn.setOutputLayer(new OutputLayer(context, 2));
    }
}
