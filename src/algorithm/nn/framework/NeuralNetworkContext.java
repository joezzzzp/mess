package algorithm.nn.framework;

import algorithm.nn.framework.func.ActiveFunction;
import algorithm.nn.framework.func.LossFunction;

/**
 * @author created by zzz at 2019/11/22 14:51
 */

public class NeuralNetworkContext {

    private int inputSize;

    private int outPutSize;

    private int[] hiddenLayerNeuronNumber;

    private ActiveFunction activeFunction;

    private LossFunction lossFunction;

    private double learnRate;

    private InitStrategy initStrategy;

    public int getInputSize() {
        return inputSize;
    }

    public void setInputSize(int inputSize) {
        this.inputSize = inputSize;
    }

    public int getOutPutSize() {
        return outPutSize;
    }

    public void setOutPutSize(int outPutSize) {
        this.outPutSize = outPutSize;
    }

    public int[] getHiddenLayerNeuronNumber() {
        return hiddenLayerNeuronNumber;
    }

    public void setHiddenLayerNeuronNumber(int[] hiddenLayerNeuronNumber) {
        this.hiddenLayerNeuronNumber = hiddenLayerNeuronNumber;
    }

    public ActiveFunction getActiveFunction() {
        return activeFunction;
    }

    public void setActiveFunction(ActiveFunction activeFunction) {
        this.activeFunction = activeFunction;
    }

    public LossFunction getLossFunction() {
        return lossFunction;
    }

    public void setLossFunction(LossFunction lossFunction) {
        this.lossFunction = lossFunction;
    }

    public double getLearnRate() {
        return learnRate;
    }

    public void setLearnRate(double learnRate) {
        this.learnRate = learnRate;
    }

    public InitStrategy getInitStrategy() {
        return initStrategy;
    }

    public void setInitStrategy(InitStrategy initStrategy) {
        this.initStrategy = initStrategy;
    }
}
