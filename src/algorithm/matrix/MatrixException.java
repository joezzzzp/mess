package algorithm.matrix;

/**
 * @author created by zzz at 2019/11/26 11:18
 */

public class MatrixException extends RuntimeException{

    public MatrixException() {
        super("Matrix exception");
    }

    public MatrixException(String message) {
        super(message);
    }
}
