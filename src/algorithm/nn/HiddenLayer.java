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

    @Override
    public void backward(double[] output, double[][] base) {
        int lastSize = neurons[0].getWeights().length;
        this.base = new double[neuronNumber][lastSize];
        double learnRate = context.getLearnRate();
        for (int i = 0; i < neuronNumber; i++) {
            Neuron n = neurons[i];
            double outputPart = 0;
            for (double[] doubles : base) {
                outputPart += doubles[i];
            }
            double hiddenPart = output[i] * (1 - output[i]);
            double[] weights = n.getWeights();
            for (int j = 0; j < n.getWeights().length; j++) {
                double inputPart = input[j];
                double outAndHidden = outputPart * hiddenPart;
                this.base[i][j] = outAndHidden;
                double finalResult = outAndHidden * inputPart;
                weights[j] -= finalResult * learnRate;
            }
        }
    }
}
