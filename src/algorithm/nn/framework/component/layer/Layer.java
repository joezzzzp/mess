package algorithm.nn.framework.component.layer;

import algorithm.nn.framework.component.neuron.Neuron;

/**
 * @author created by zzz at 2019/11/22 17:45
 */

public interface Layer {

    void setNeuron(int position, Neuron neuron);

    double[] forward(double[] input);

    void backward(double[] output, double[][] base);

    void init();

    double[][] getBase();

    void setBase(double[][] base);

    double[] getInput();
}
