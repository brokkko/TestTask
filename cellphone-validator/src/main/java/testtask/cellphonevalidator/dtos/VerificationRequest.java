package testtask.cellphonevalidator.dtos;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@Builder
public class VerificationRequest {
    private String phoneNumber;
    private String code;
}
