package testtask.cellphonevalidator.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
@Builder
public class VerificationData {
    private String code;
    private long expiryTime;
    private long createdTime;
    private int verifyAttempts;

    /**
     * This function increments the number of verify attempts.
     * <p>
     * It does not accept any parameters nor does it return any
     * value.
     */
    public void incrementVerifyAttempts() {
        this.verifyAttempts++;
    }

}
