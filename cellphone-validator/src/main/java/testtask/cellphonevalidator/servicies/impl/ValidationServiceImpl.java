package testtask.cellphonevalidator.servicies.impl;

import testtask.cellphonevalidator.dtos.VerificationRequest;
import testtask.cellphonevalidator.enums.Constants;
import testtask.cellphonevalidator.exceptions.*;
import testtask.cellphonevalidator.models.VerificationData;
import testtask.cellphonevalidator.servicies.StorageService;
import testtask.cellphonevalidator.servicies.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import testtask.smssupplier.servicies.SmsSupplierService;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {

    private final SmsSupplierService smsService;
    private final StorageService storageService;

    @Override
    public void sendCode(String phoneNumber) {
        Optional<VerificationData> data = storageService.getVerificationData(phoneNumber);
        if (data.isPresent() && System.currentTimeMillis() - data.get().getCreatedTime() < Constants.CODE_RECREATION_TIME.getValue()) {
            throw new SMSLimitReachedException("Too many attempts to send SMS.");
        } else if (data.isPresent()) {
            storageService.deleteVerificationDataByPhoneNumber(phoneNumber);
        }
        if (!isPhoneNumberValid(phoneNumber)) {
            throw new InvalidPhoneNumberException("Invalid phone number.");
        }
        String code = generateCode();
        long expiryTime = System.currentTimeMillis() + Constants.CODE_EXPIRY_TIME.getValue();
        storageService.addVerificationData(
                phoneNumber,
                VerificationData.builder()
                        .code(code)
                        .expiryTime(expiryTime)
                        .createdTime(System.currentTimeMillis())
                        .verifyAttempts(1).build());
        smsService.sendSms(phoneNumber, code);
    }

    @Override
    public boolean verifyCode(VerificationRequest request) {
        Optional<VerificationData> data = storageService.getVerificationData(request.getPhoneNumber());
        if (data.isEmpty()) {
            throw new PhoneNumberNotFoundException(request.getPhoneNumber());
        }
        if (data.get().getVerifyAttempts() >= Constants.MAX_ATTEMPTS.getValue()) {
            storageService.deleteVerificationDataByPhoneNumber(request.getPhoneNumber());
            throw new MaxVerificationAttemptsReachedException("Too many verification attempts. Request a new code.");
        }
        if (System.currentTimeMillis() > data.get().getExpiryTime() || !isEqual(data.get().getCode().getBytes(), request.getCode().getBytes())) {
            data.get().incrementVerifyAttempts();
            throw new InvalidVerificationCodeException("Invalid code.");
        }
        return true;
    }

    /**
     * This function generates a unique secure 4-digit code.
     * <p>
     * It uses the SecureRandom class to generate random numbers and appends them
     * to a StringBuilder object until the desired code length is reached.
     *
     * @return a unique secure 4-digit code as a String
     */
    private String generateCode() {
        SecureRandom random = new SecureRandom();
        int codeLength = 4;
        StringBuilder code = new StringBuilder(codeLength);
        for (int i = 0; i < codeLength; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    /**
     * Validates a phone number.
     * <p>
     * This method will return true for phone numbers
     * that start with a '+' sign, followed by 6 to 14 digits. The digits may be
     * separated by spaces.
     *
     * @param phoneNumber The phone number to be validated
     * @return True if the phone number is valid, false otherwise
     */
    private boolean isPhoneNumberValid(String phoneNumber) {
        String pattern = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    /**
     * Safe method that compares two byte arrays.
     * <p>
     * This method saves an equal operation from Timing Attack.
     *
     * @param a Fist byte array
     * @param b Second byte array
     * @return True if two byte arrays are equal, false otherwise
     */
    private boolean isEqual(byte[] a, byte[] b) {
        if (a.length != b.length) {
            return false;
        }
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result |= a[i] ^ b[i];
        }
        return result == 0;
    }
}
