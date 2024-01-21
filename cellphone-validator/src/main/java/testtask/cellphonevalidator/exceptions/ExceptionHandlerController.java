package testtask.cellphonevalidator.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    /**
     * This function handles exceptions with a BAD_REQUEST status.
     *
     * @param e RuntimeException to be handled.
     * @return ResponseEntity with the BAD_REQUEST status and body.
     */
    @ExceptionHandler(value = {InvalidVerificationCodeException.class, InvalidPhoneNumberException.class})
    public ResponseEntity<?> handleBadRequestException(RuntimeException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);
    }

    /**
     * This function handles exceptions with a TOO_MANY_REQUESTS status.
     *
     * @param e RuntimeException to be handled.
     * @return ResponseEntity with the TOO_MANY_REQUESTS status and body.
     */
    @ExceptionHandler(value = {SMSLimitReachedException.class, MaxVerificationAttemptsReachedException.class,})
    public ResponseEntity<?> handleTooManyRequestsException(RuntimeException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .body(exceptionResponse);
    }

    /**
     * This function handles exceptions with a NOT_FOUND status.
     *
     * @param e RuntimeException to be handled.
     * @return ResponseEntity with NOT_FOUND status and body.
     */
    @ExceptionHandler(value = {PhoneNumberNotFoundException.class})
    public ResponseEntity<?> handleNotFoundException(RuntimeException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);
    }

}
