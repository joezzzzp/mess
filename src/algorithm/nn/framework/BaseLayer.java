package algorithm.nn.framework;

import algorithm.nn.Neuron;

/**
 * @author created by zzz at 2019/11/22 14:30
 */

public abstract class BaseLayer extends BaseNeuralNetworkComponent implements Layer {

    protected double bias;

    protected double[] input;

    protected int neuronNumber;

    protected Neuron[] neurons;

    protected double[][] base;

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
            return;
        }
        throw new NeuralNetworkException("Out of bounds");
    }

    @Override
    public double[] forward(double[] input) {
        this.input = input;
        double[] result = new double[neurons.length];
        for (int i = 0; i < neurons.length; i++) {
            Neuron neuron = neurons[i];
            result[i] = context.getActiveFunction().active(neuron.outPutWithOutBiasAndActive(input) + bias);
        }
        return result;
    }

    @Override
    public double[] getInput() {
        return input;
    }

    @Override
    public void setBase(double[][] base) {
        this.base = base;
    }

    @Override
    public double[][] getBase() {
        return base;
    }
}
