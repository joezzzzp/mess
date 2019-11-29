package algorithm.nn.framework.component.layer;

import algorithm.nn.framework.InitStrategy;
import algorithm.nn.framework.NeuralNetworkContext;
import algorithm.nn.framework.component.neuron.Neuron;

/**
 * @author created by zzz at 2019/11/22 18:12
 */

public class OutputLayer extends BaseLayer {

    public OutputLayer(NeuralNetworkContext context) {
        super(context);
    }

    public OutputLayer(NeuralNetworkContext context, double bias) {
        super(context, bias);
    }

    @Override
    public void init() {
        neuronNumber = context.getOutPutSize();
        neurons = new Neuron[neuronNumber];
        if (context.getInitStrategy() == InitStrategy.RANDOM) {
            int inputSize = context.getHiddenLayerNeuronNumber()[context.getHiddenLayerNeuronNumber().length - 1];
            randomInitNeuron(inputSize);
        }
    }

    @Override
    public void backward(double[] output, double[][] base) {
        double[] expected = base[0];
        int lastSize = neurons[0].getWeights().length;
        this.base = new double[neuronNumber][lastSize];
        double learnRate = context.getLearnRate();
        for (int i = 0; i < neuronNumber; i++) {
            Neuron n = neurons[i];
            double outputPart = (output[i] - expected[i]) * context.getActiveFunction().backward(output[i]);
            double[] weights = n.getWeights();
            for (int j = 0; j < weights.length; j++) {
                this.base[i][j] = outputPart * n.getWeights()[j];
                double inputPart = input[j];
                weights[j] -= outputPart * inputPart * learnRate;
            }
        }
    }
}
