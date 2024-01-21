package testtask.cellphonevalidator.servicies;

import testtask.cellphonevalidator.dtos.VerificationRequest;

public interface ValidationService {

    /**
     * This function sends a code to a specified phone number.
     *
     * @param phoneNumber The phone number to send the code to
     */
    void sendCode(String phoneNumber);

    /**
     * Verifies the provided verification request.
     *
     * @param request A VerificationRequest object containing the
     *                details required for verification.
     * @return True if the verification is successful.
     */
    boolean verifyCode(VerificationRequest request);
}
