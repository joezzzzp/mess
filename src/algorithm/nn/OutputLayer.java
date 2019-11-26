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

    @Override
    public void backward(double[] output, double[][] base) {
        double[] expected = base[0];
        int lastSize = neurons[0].getWeights().length;
        this.base = new double[neuronNumber][lastSize];
        double learnRate = context.getLearnRate();
        for (int i = 0; i < neuronNumber; i++) {
            Neuron n = neurons[i];
            double outputPart = -1 * (expected[i] - output[i]) * output[i] * (1 - output[i]);
            double[] weights = n.getWeights();
            for (int j = 0; j < weights.length; j++) {
                this.base[i][j] = outputPart * n.getWeights()[j];
                double inputPart = input[j];
                weights[j] -= outputPart * inputPart * learnRate;
            }
        }
    }
}
