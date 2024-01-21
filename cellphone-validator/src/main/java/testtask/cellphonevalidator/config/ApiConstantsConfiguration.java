package testtask.cellphonevalidator.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api")
@Getter
public class ApiConstantsConfiguration {
    public static final String API = "/api";
    public static final String VALIDATION = API + "/validation";
}
