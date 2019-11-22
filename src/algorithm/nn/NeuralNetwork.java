package algorithm.nn;

import algorithm.nn.framework.BaseNeuralNetworkComponent;
import algorithm.nn.framework.Layer;
import algorithm.nn.framework.NeuralNetworkContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author created by zzz at 2019/11/22 14:20
 */

public class NeuralNetwork extends BaseNeuralNetworkComponent {

    private List<Layer> layers = new ArrayList<>();

    private boolean hasOutput = false;

    public NeuralNetwork(NeuralNetworkContext context) {
        super(context);
    }

    public void setInputLayer(InputLayer layer) {
        layers.add(0, layer);
    }

    public void setOutputLayer(OutputLayer layer) {
        layers.add(layer);
        hasOutput = true;
    }

    public void addHiddenLayer(HiddenLayer layer) {
        if (hasOutput) {
            layers.add(layers.size() - 1, layer);
        } else {
            layers.add(layer);
        }
    }

    public double[] forward(double[] input) {
        if (!layers.isEmpty()) {
            double[] last = input;
            for (Layer layer : layers) {
                last = layer.forward(last);
            }
            return last;
        }
        return new double[0];
    }
}
