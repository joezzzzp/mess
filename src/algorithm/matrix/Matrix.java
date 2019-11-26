package algorithm.matrix;

import algorithm.nn.framework.NeuralNetworkException;

/**
 * @author created by zzz at 2019/11/26 10:47
 */

public class Matrix {

    private double[][] value;

    private int rowNumber = 0;

    private int columnNumber = 0;

    /**
     * 0 matrix 1 vector
     */
    private int type = 0;

    public double[][] getValue() {
        return value;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public int getType() {
        return type;
    }

    public Matrix(double[][] value) {
        if (value.length == 0) {
            throw new MatrixException("Empty matrix");
        }
        if (value[0].length == 0) {
            throw new MatrixException("Empty matrix");
        }
        this.value = value;
        this.rowNumber = value.length;
        this.columnNumber = value[0].length;
        if (rowNumber == 1) {
            type = 1;
        }
    }

    public void print() {
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                System.out.print(value[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public Matrix matrixDot(Matrix matrix) {
        if (rowNumber == matrix.getColumnNumber() && columnNumber == matrix.getRowNumber()) {
            double[][] m = getValue();
            double[][] nt = matrix.traverse().getValue();
            double[][] result = new double[m.length][nt.length];
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < nt.length; j++) {
                    result[i][j] = vectorDot(m[i], nt[j]);
                }
            }
            return new Matrix(result);
        }
        throw new NeuralNetworkException("Two matrix not match");
    }

    public Matrix elementDot(Matrix matrix) {
        if (rowNumber == matrix.getRowNumber() && columnNumber == matrix.getColumnNumber()) {
            double[][] result = new double[rowNumber][columnNumber];
            double[][] n = matrix.getValue();
            for (int i = 0; i < rowNumber; i++) {
                for (int j = 0; j < columnNumber; j++) {
                    result[i][j] = value[i][j] * n[i][j];
                }
            }
            return new Matrix(result);
        }
        throw new NeuralNetworkException("Two matrix not match");
    }

    public double vectorDot(double[] row, double[] column) {
        double result = 0;
        for (int i = 0; i < row.length; i++) {
            result += row[i] * column[i];
        }
        return result;
    }

    public Matrix traverse() {
        double[][] traversed = new double[columnNumber][rowNumber];
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                traversed[j][i] = value[i][j];
            }
        }
        return new Matrix(traversed);
    }
}
