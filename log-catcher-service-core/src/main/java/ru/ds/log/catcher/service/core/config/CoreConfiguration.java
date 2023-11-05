package ru.ds.log.catcher.service.core.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.ds.log.catcher.service.common.config.CommonConfiguration;
import ru.ds.log.catcher.service.dao.DaoConfiguration;

@Configuration
@Import({
        CommonConfiguration.class,
        DaoConfiguration.class,
})
@EnableScheduling
@EnableConfigurationProperties
public class CoreConfiguration {
}
