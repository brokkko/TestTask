package testtask.cellphonevalidator.exceptions;

public class SMSLimitReachedException extends RuntimeException {
    public SMSLimitReachedException(String message) {
        super(message);
    }
}
