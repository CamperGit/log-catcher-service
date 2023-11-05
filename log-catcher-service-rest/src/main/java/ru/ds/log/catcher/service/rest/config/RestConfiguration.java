package ru.ds.log.catcher.service.rest.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.ds.log.catcher.service.telegram.config.TelegramConfiguration;

@Configuration
@Import({
        TelegramConfiguration.class,
        SwaggerConfiguration.class
})
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class RestConfiguration {
}
