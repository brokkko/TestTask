package testtask.cellphonevalidator.servicies;

import testtask.cellphonevalidator.models.VerificationData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StorageServiceTest {

    @Autowired
    private StorageService storageService;

    @Test
    void deleteVerificationDataByPhoneNumber() {
        String phoneNumber = "+1234567890";
        VerificationData data = VerificationData.builder()
                .code("1234")
                .createdTime(System.currentTimeMillis())
                .verifyAttempts(1)
                .build();
        storageService.addVerificationData(phoneNumber, data);
        storageService.deleteVerificationDataByPhoneNumber(phoneNumber);
        assertEquals(Optional.empty(), storageService.getVerificationData(phoneNumber));
    }

    @Test
    void getVerificationData() {
        String phoneNumber = "+1234567891";
        VerificationData data = VerificationData.builder()
                .code("1234")
                .createdTime(System.currentTimeMillis())
                .verifyAttempts(1)
                .build();
        storageService.addVerificationData(phoneNumber, data);
        assertEquals(Optional.of(data), storageService.getVerificationData(phoneNumber));
    }
}
