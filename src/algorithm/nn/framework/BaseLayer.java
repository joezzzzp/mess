package algorithm.nn.framework;

import algorithm.nn.Neuron;

/**
 * @author created by zzz at 2019/11/22 14:30
 */

public abstract class BaseLayer extends BaseNeuralNetworkComponent implements Layer {

    private double bias;

    private int neuronNumber;

    private Neuron[] neurons;

    public BaseLayer(NeuralNetworkContext context, int neuronNumber) {
        this(context, neuronNumber, 0.0);
    }

    public BaseLayer(NeuralNetworkContext context, int neuronNumber, double bias) {
        super(context);
        neurons = new Neuron[neuronNumber];
        this.neuronNumber = neuronNumber;
        this.bias = bias;
    }

    @Override
    public void setNeuron(int position, Neuron neuron) {
        if (position >= 0 && position < neurons.length) {
            neurons[position] = neuron;
        }
        throw new NeuralNetworkException("Out of bounds");
    }

    @Override
    public double[] forward(double[] input) {
        double[] result = new double[neurons.length];
        for (int i = 0; i < neurons.length; i++) {
            Neuron neuron = neurons[i];
            result[i] = context.getActiveFunction().active(neuron.outPutWithOutBiasAndActive(input) + bias);
        }
        return result;
    }

    @Override
    public void backward() {

    }
}
