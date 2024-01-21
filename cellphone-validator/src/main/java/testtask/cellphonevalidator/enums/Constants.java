package testtask.cellphonevalidator.enums;

import lombok.Getter;

@Getter
public enum Constants {
    CODE_EXPIRY_TIME(5 * 60 * 1000), // 5 minutes in milliseconds
    CODE_RECREATION_TIME(60 * 1000), // 1 minute in milliseconds
    MAX_ATTEMPTS(3),
    MAX_SMS_REQUESTS(3);

    private final int value;

    Constants(int value) {
        this.value = value;
    }
}
