package algorithm.nn;

import algorithm.nn.framework.InitStrategy;
import algorithm.nn.framework.NeuralNetwork;
import algorithm.nn.framework.NeuralNetworkContext;
import algorithm.nn.framework.func.Sigmoid;
import algorithm.nn.framework.func.SquareError;

import java.util.*;

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
        context.setInputSize(32);
        context.setHiddenLayerNeuronNumber(new int[]{14});
        context.setOutPutSize(1);
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
            if (i % 10 == 0) {
                System.out.println("迭代第" + (i + 1) + "次：");
                test(nn, testSet);
            }
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
            String ret = result[0] >= 0.5 ? "偶数" : "奇数";
            System.out.println(value + "是" + ret);
        }
    }

    private static void train(NeuralNetwork neuralNetwork, Map<Integer, Boolean> trainSet) {
        for (Map.Entry<Integer, Boolean> item : trainSet.entrySet()) {
            neuralNetwork.setInput(toBinaryNum(item.getKey()));
            neuralNetwork.setExpected(new double[]{ convert(item.getValue()) });
            neuralNetwork.forward();
            neuralNetwork.backward();
        }
    }

    private static void test(NeuralNetwork neuralNetwork, Map<Integer, Boolean> testSet) {
        int rightCount = 0;
        for (Map.Entry<Integer, Boolean> item : testSet.entrySet()) {
            neuralNetwork.setInput(toBinaryNum(item.getKey()));
            double result = neuralNetwork.forward()[0];
            boolean isEven = result >= 0.5;
            if (isEven == item.getValue()) {
                rightCount++;
            }
        }
        System.out.println("测试集准确率：" + (double) rightCount / (double) testSet.size());
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

    private static double[] toBinaryNum(int num)  //将十进制转为二进制
    {
        double[] binaryNum = new double[32];
        for(int i = 31; i >= 0; i--)
        {
            binaryNum[i] = num % 2;
            num /= 2;
        }
        return binaryNum;
    }
}
