package testtask.cellphonevalidator.servicies;

import testtask.cellphonevalidator.models.VerificationData;

import java.util.Optional;

public interface StorageService {
    /**
     * Retrieves the verification data associated with a given phone number.
     *
     * @param phoneNumber The phone number to retrieve verification data for.
     * @return Optional containing VerificationData if it exists.
     */
    Optional<VerificationData> getVerificationData(String phoneNumber);

    /**
     * This function adds verification data for a given phone number.
     *
     * @param phoneNumber The phone number to which the data is associated.
     * @param data        The verification data to be added.
     */
    void addVerificationData(String phoneNumber, VerificationData data);

    /**
     * Deletes the verification data associated with the given phone number.
     *
     * @param phoneNumber The phone number whose verification data is to be deleted
     */
    void deleteVerificationDataByPhoneNumber(String phoneNumber);
}
