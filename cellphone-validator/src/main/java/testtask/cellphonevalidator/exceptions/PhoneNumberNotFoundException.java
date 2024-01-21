package testtask.cellphonevalidator.exceptions;

public class PhoneNumberNotFoundException extends RuntimeException {

    public PhoneNumberNotFoundException(String phoneNumber) {
        super("Phone number " + phoneNumber + " not found");
    }
}
