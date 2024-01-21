package testtask.cellphonevalidator.exceptions;

public class MaxVerificationAttemptsReachedException extends RuntimeException {
    public MaxVerificationAttemptsReachedException(String message) {
        super(message);
    }
}
