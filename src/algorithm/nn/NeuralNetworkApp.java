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

        HiddenLayer hiddenLayer = new HiddenLayer(context, 2, 0.35);
        Neuron h1 = new Neuron(context, 2);
        h1.setWeights(new double[]{0.15, 0.20});
        Neuron h2 = new Neuron(context, 2);
        h2.setWeights(new double[]{0.25, 0.30});
        hiddenLayer.setNeuron(0, h1);
        hiddenLayer.setNeuron(1, h2);

        OutputLayer outputLayer = new OutputLayer(context, 2, 0.60);
        Neuron o1 = new Neuron(context, 2);
        Neuron o2 = new Neuron(context, 2);
        o1.setWeights(new double[]{0.40, 0.45});
        o2.setWeights(new double[]{0.50, 0.55});
        outputLayer.setNeuron(0, o1);
        outputLayer.setNeuron(1, o2);

        nn.setInputLayer(inputLayer);
        nn.addHiddenLayer(hiddenLayer);
        nn.setOutputLayer(outputLayer);

        print(nn.forward(new double[]{0.05, 0.10}));

    }

    private static void print(double[] result) {
        for (double d : result) {
            System.out.print(d);
            System.out.print(" ");
        }
        System.out.println();
    }
}
