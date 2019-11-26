package algorithm.nn;

import algorithm.nn.framework.NeuralNetworkContext;
import algorithm.nn.framework.SquareError;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author created by zzz at 2019/11/22 18:31
 */

public class NeuralNetworkApp {

    //https://blog.csdn.net/weixin_38347387/article/details/82936585
    public static void main(String[] args) {
        NeuralNetworkContext context = new NeuralNetworkContext();
        context.setActiveFunction(new Sigmoid());
        context.setLossFunction(new SquareError());
        context.setLearnRate(0.1);
        NeuralNetwork nn = new NeuralNetwork(context);
        InputLayer inputLayer = new InputLayer(context, 1);

        HiddenLayer hiddenLayer = new HiddenLayer(context, 2, 0.00);
        Neuron h1 = new Neuron(context, 1);
        h1.setWeights(new double[]{0.03});
        Neuron h2 = new Neuron(context, 1);
        h2.setWeights(new double[]{0.98});
        hiddenLayer.setNeuron(0, h1);
        hiddenLayer.setNeuron(1, h2);

        HiddenLayer hiddenLayer2 = new HiddenLayer(context, 2, 0.00);
        Neuron h21 = new Neuron(context, 2);
        h21.setWeights(new double[]{0.08, 0.73});
        Neuron h22 = new Neuron(context, 2);
        h22.setWeights(new double[]{0.2, 0.005});
        hiddenLayer2.setNeuron(0, h21);
        hiddenLayer2.setNeuron(1, h22);

        OutputLayer outputLayer = new OutputLayer(context, 1, 0.00);
        Neuron o1 = new Neuron(context, 2);
        o1.setWeights(new double[]{0.648, 0.789});
        outputLayer.setNeuron(0, o1);

        nn.setInputLayer(inputLayer);
        nn.addHiddenLayer(hiddenLayer);
        nn.addHiddenLayer(hiddenLayer2);
        nn.setOutputLayer(outputLayer);

        Map<Integer, Boolean> trainSet = new HashMap<>();
        Map<Integer, Boolean> testSet = new HashMap<>();
        buildSet(trainSet, testSet);

        for (int i = 0; i < 10000; i++) {
            System.out.println("迭代第" + i + "次：");
            train(nn, trainSet);
            test(nn, testSet);
        }
    }

    private static void train(NeuralNetwork neuralNetwork, Map<Integer, Boolean> trainSet) {
        for (Map.Entry<Integer, Boolean> item : trainSet.entrySet()) {
            double[] input = new double[]{ (double) item.getKey() / 10000.0 };
            double[] expected = new double[]{ convert(item.getValue()) };
            neuralNetwork.setInput(input);
            neuralNetwork.setExpected(expected);
            neuralNetwork.forward();
            neuralNetwork.backward();
        }
    }

    private static void test(NeuralNetwork neuralNetwork, Map<Integer, Boolean> testSet) {
        int rightCount = 0;
        for (Map.Entry<Integer, Boolean> item : testSet.entrySet()) {
            double[] input = new double[]{(double) item.getKey() / 10000.0 };
            neuralNetwork.setInput(input);
            double result = neuralNetwork.forward()[0];
            boolean isEven = result >= 0.5;
            if (isEven == item.getValue()) {
                rightCount++;
            }
        }
        System.out.println("准确率：" + (double) rightCount / (double) testSet.size());
    }

    private static double convert(boolean b) {
        return b ? 1 : 0;
    }

    //true 偶数 false 奇数
    private static void buildSet(Map<Integer, Boolean> trainSet, Map<Integer, Boolean> testSet) {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            if (random.nextInt(10) == 0) {
                testSet.put(i, i % 2 == 0);
                continue;
            }
            trainSet.put(i, i % 2 == 0);
        }
    }

    private static void print(double[] result) {
        for (double d : result) {
            System.out.print(d);
            System.out.print(" ");
        }
    }
}
