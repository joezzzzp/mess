package algorithm.nn;

import algorithm.nn.framework.NeuralNetworkContext;

/**
 * @author created by zzz at 2019/11/22 18:31
 */

public class NeuralNetworkApp {

    //https://blog.csdn.net/weixin_38347387/article/details/82936585
    public static void main(String[] args) {
        NeuralNetworkContext context = new NeuralNetworkContext();
        context.setActiveFunction(new Sigmoid());
        NeuralNetwork nn = new NeuralNetwork(context);
        InputLayer inputLayer = new InputLayer(context, 2);
        HiddenLayer hiddenLayer = new HiddenLayer(context, 2);
        OutputLayer outputLayer = new OutputLayer(context, 2);
        nn.setInputLayer(inputLayer);
        nn.addHiddenLayer(hiddenLayer);
        nn.setOutputLayer(outputLayer);
    }
}
