package testtask.cellphonevalidator.servicies;

import testtask.cellphonevalidator.dtos.VerificationRequest;
import testtask.cellphonevalidator.exceptions.InvalidPhoneNumberException;
import testtask.cellphonevalidator.exceptions.InvalidVerificationCodeException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ValidationServiceTest {
    @Autowired
    private ValidationService validationService;

    @Test
    void sendCode() {
        String validPhoneNumber = "+1234567891";
        assertAll(() -> validationService.sendCode(validPhoneNumber));
    }

    @Test
    void sendCodeWithInvalidPhoneNumber() {
        String invalidPhoneNumber = "1234";
        assertThrows(InvalidPhoneNumberException.class, () -> validationService.sendCode(invalidPhoneNumber));
    }

    @Test
    void verifyCodeWithIncorrectCode() {
        String phoneNumber = "+1234567890";
        String incorrectCode = "incorrect";
        validationService.sendCode(phoneNumber);
        VerificationRequest request = new VerificationRequest(phoneNumber, incorrectCode);
        assertThrows(InvalidVerificationCodeException.class, () -> validationService.verifyCode(request));
    }
}
