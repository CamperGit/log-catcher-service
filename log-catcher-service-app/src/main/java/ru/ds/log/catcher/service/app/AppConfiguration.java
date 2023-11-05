package ru.ds.log.catcher.service.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.ds.log.catcher.service.rest.config.RestConfiguration;

@Configuration
@Import({
        RestConfiguration.class
})
@ComponentScan(basePackages = { "ru.ds.log.catcher.service" })
public class AppConfiguration {
}
