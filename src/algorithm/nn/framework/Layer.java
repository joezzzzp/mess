package algorithm.nn.framework;

import algorithm.nn.Neuron;

/**
 * @author created by zzz at 2019/11/22 17:45
 */

public interface Layer {

    void setNeuron(int position, Neuron neuron);

    double[] forward(double[] input);

    void backward();
}
