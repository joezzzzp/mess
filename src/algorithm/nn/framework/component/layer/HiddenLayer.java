package algorithm.nn.framework.component.layer;

import algorithm.nn.framework.InitStrategy;
import algorithm.nn.framework.NeuralNetworkContext;
import algorithm.nn.framework.component.neuron.Neuron;

/**
 * @author created by zzz at 2019/11/22 18:11
 */

public class HiddenLayer extends BaseLayer {

    private int index;

    public HiddenLayer(NeuralNetworkContext context, int index) {
        super(context);
        this.index = index;
    }

    public HiddenLayer(NeuralNetworkContext context, int index, double bias) {
        super(context, bias);
        this.index = index;
    }

    @Override
    public void init() {
        neuronNumber = context.getHiddenLayerNeuronNumber()[index];
        neurons = new Neuron[neuronNumber];
        if (context.getInitStrategy() == InitStrategy.RANDOM) {
            int inputSize = context.getInputSize();
            if (index > 0) {
                inputSize = context.getHiddenLayerNeuronNumber()[index - 1];
            }
            randomInitNeuron(inputSize);
        }
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
