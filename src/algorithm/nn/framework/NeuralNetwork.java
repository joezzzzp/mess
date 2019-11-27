package algorithm.nn.framework;

import algorithm.nn.framework.component.BaseNeuralNetworkComponent;
import algorithm.nn.framework.component.layer.HiddenLayer;
import algorithm.nn.framework.component.layer.InputLayer;
import algorithm.nn.framework.component.layer.Layer;
import algorithm.nn.framework.component.layer.OutputLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author created by zzz at 2019/11/22 14:20
 */

public class NeuralNetwork extends BaseNeuralNetworkComponent {

    private List<Layer> layers = new ArrayList<>();

    private boolean hasOutput = false;

    private double[] input;

    private double[] outPut;

    private double[] expected;

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

    public void setInput(double[] input) {
        this.input = input;
    }

    public double[] forward() {
        if (!layers.isEmpty()) {
            double[] last = input;
            for (Layer layer : layers) {
                last = layer.forward(last);
            }
            outPut = last;
            return last;
        }
        return new double[0];
    }

    public void setExpected(double[] expected) {
        this.expected = expected;
    }

    public void backward() {
        double[] a = outPut;
        double[][] b = new double[1][expected.length];
        b[0] = expected;
        for (int i = layers.size() - 1; i >= 0; i--) {
            Layer currentLayer = layers.get(i);
            currentLayer.backward(a, b);
            a = currentLayer.getInput();
            b = currentLayer.getBase();
        }
    }

    public void build() {
        //输入层
        InputLayer inputLayer = new InputLayer(context);
        inputLayer.init();
        setInputLayer(inputLayer);

        //隐含层
        for (int i = 0; i < context.getHiddenLayerNeuronNumber().length; i++) {
            HiddenLayer hiddenLayer = new HiddenLayer(context, i);
            hiddenLayer.init();
            addHiddenLayer(hiddenLayer);
        }

        //输出层
        OutputLayer outputLayer = new OutputLayer(context);
        outputLayer.init();
        setOutputLayer(outputLayer);
    }

    public double calLoss() {
        return context.getLossFunction().calLoss(outPut, expected);
    }
}
