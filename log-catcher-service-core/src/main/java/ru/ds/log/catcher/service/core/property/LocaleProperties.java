package ru.ds.log.catcher.service.core.property;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Data
@Validated
@Configuration
@ConfigurationProperties("locale")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocaleProperties {

    @NotEmpty(message = "Local tag property not found")
    String tag;
}
