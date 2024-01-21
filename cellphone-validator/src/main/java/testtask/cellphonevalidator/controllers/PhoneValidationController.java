package testtask.cellphonevalidator.controllers;

import testtask.cellphonevalidator.dtos.VerificationRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static testtask.cellphonevalidator.config.ApiConstantsConfiguration.VALIDATION;

@RequestMapping(value = VALIDATION)
public interface PhoneValidationController {

    /**
     * This is a POST API operation that sends a verification code to a provided
     * phone number.
     *
     * @param phoneNumber The phone number to send the verification code to.
     * @return ResponseEntity with HTTP status indicating the
     * outcome of the operation.
     */
    @ApiOperation(value = "Send verification code")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Code sent successfully"),
            @ApiResponse(code = 400, message = "Invalid phone number pattern"),
            @ApiResponse(code = 429, message = "Too many attempts to send SMS in the last minute")
    })
    @PostMapping("/sendCode")
    ResponseEntity<HttpStatus> sendCode(@RequestBody String phoneNumber);

    /**
     * This is an API endpoint that verifies a provided code.
     * It responds with different messages based on the result of verification.
     *
     * @param request ResponseEntity with HTTP status indicating the
     *                outcome of the operation.
     * @return ResponseEntity with HTTP status indicating the
     * outcome of the operation.
     */
    @ApiOperation(value = "Verify code")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Code verified"),
            @ApiResponse(code = 400, message = "Invalid code from SMS"),
            @ApiResponse(code = 429, message = "Too many verification attempts")
    })
    @PostMapping("/verifyCode")
    ResponseEntity<HttpStatus> verifyCode(@RequestBody VerificationRequest request);
}
