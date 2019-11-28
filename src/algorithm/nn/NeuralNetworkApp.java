package algorithm.nn;

import algorithm.nn.framework.InitStrategy;
import algorithm.nn.framework.NeuralNetwork;
import algorithm.nn.framework.NeuralNetworkContext;
import algorithm.nn.framework.func.ActiveFunction;
import algorithm.nn.framework.func.LossFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * @author created by zzz at 2019/11/22 18:31
 */

public class NeuralNetworkApp {

    //https://blog.csdn.net/weixin_38347387/article/details/82936585
    public static void main(String[] args) {
        NeuralNetworkContext context = new NeuralNetworkContext();
        context.setActiveFunction(ActiveFunction.SIGMOID);
        context.setLossFunction(LossFunction.SQUARE_ERROR);
        context.setLearnRate(0.1);
        context.setInputSize(32);
        context.setHiddenLayerNeuronNumber(new int[]{8, 8});
        context.setOutPutSize(2);
        context.setInitStrategy(InitStrategy.RANDOM);
        NeuralNetwork nn = new NeuralNetwork(context);
        nn.build();

        Map<Integer, Boolean> trainSet = new HashMap<>();
        Map<Integer, Boolean> testSet = new HashMap<>();
        buildSet(trainSet, testSet);

        System.out.println("未训练时：");
        test(nn, testSet);
        apply(nn);
        for (int i = 0; i < 100; i++) {
            train(nn, trainSet);
            System.out.println("迭代第" + (i + 1) + "次：");
            test(nn, testSet);
        }
        apply(nn);
    }

    private static void apply(NeuralNetwork neuralNetwork) {
        System.out.println("请输入要测试的数字（0 - 10000，输入范围之外的数字退出）：");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int value = scanner.nextInt();
            if (value > 10000 || value < 0) {
                break;
            }
            neuralNetwork.setInput(toBinaryNum(value));
            double[] result = neuralNetwork.forward();
            print(result);
            int whichClass = findClass(result);
            String ret = "未知";
            if (whichClass == 0) {
                ret = "奇数";
            } else if (whichClass == 1) {
                ret = "偶数";
            }
            System.out.println(value + "是" + ret);
        }
    }

    private static void train(NeuralNetwork neuralNetwork, Map<Integer, Boolean> trainSet) {
        for (Map.Entry<Integer, Boolean> item : trainSet.entrySet()) {
            neuralNetwork.setInput(toBinaryNum(item.getKey()));
            neuralNetwork.setExpected(convert(item.getValue()));
            neuralNetwork.forward();
            neuralNetwork.backward();
        }
    }

    private static void test(NeuralNetwork neuralNetwork, Map<Integer, Boolean> testSet) {
        int rightCount = 0;
        for (Map.Entry<Integer, Boolean> item : testSet.entrySet()) {
            neuralNetwork.setInput(toBinaryNum(item.getKey()));
            double[] result = neuralNetwork.forward();
            int whichClass = findClass(result);
            int trueClass = item.getValue() ? 0 : 1;
            if (whichClass == trueClass) {
                rightCount++;
            }
        }
        System.out.println("测试集准确率：" + (double) rightCount / (double) testSet.size());
    }

    private static double[] convert(boolean isOdd) {
        double[] result = new double[2];
        if (isOdd) {
            result[0] = 1;
        } else {
            result[1] = 1;
        }
        return result;
    }

    //true 偶数 false 奇数
    private static void buildSet(Map<Integer, Boolean> trainSet, Map<Integer, Boolean> testSet) {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            if (random.nextInt(10) == 0) {
                testSet.put(i, i % 2 != 0);
                continue;
            }
            trainSet.put(i, i % 2 != 0);
        }
    }

    private static void print(double[] result) {
        for (double d : result) {
            System.out.print(d);
            System.out.print(" ");
        }
    }

    //将十进制转为二进制
    private static double[] toBinaryNum(int num) {
        double[] binaryNum = new double[32];
        for(int i = 31; i >= 0; i--)
        {
            binaryNum[i] = num % 2;
            num /= 2;
        }
        return binaryNum;
    }

    private static int findClass(double[] result) {
        int whichClass = 0;
        double max = Double.MIN_VALUE;
        for (int i = 0; i < result.length; i++) {
            if (result[i] > max) {
                max = result[i];
                whichClass = i;
            }
        }
        return whichClass;
    }
}
