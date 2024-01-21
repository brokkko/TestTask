package testtask.cellphonevalidator.controllers.impl;

import testtask.cellphonevalidator.controllers.PhoneValidationController;
import testtask.cellphonevalidator.dtos.VerificationRequest;
import testtask.cellphonevalidator.servicies.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PhoneValidationControllerImpl implements PhoneValidationController {

    private final ValidationService validationService;

    @Override
    public ResponseEntity<HttpStatus> sendCode(String phoneNumber) {
        validationService.sendCode(phoneNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> verifyCode(VerificationRequest request) {
        if (!validationService.verifyCode(request)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
