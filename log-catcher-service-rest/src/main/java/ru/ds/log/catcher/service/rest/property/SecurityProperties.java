package ru.ds.log.catcher.service.rest.property;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Validated
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    @NotEmpty(message = "System user username property not found")
    String username;

    @NotEmpty(message = "System user password property not found")
    String password;

    @NotEmpty(message = "System user roles property not found")
    List<String> roles;
}
