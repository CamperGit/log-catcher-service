package ru.ds.log.catcher.service.dao;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {
        "ru.ds.log.catcher.service.dao"
})
@EntityScan(basePackages = {
        "ru.ds.log.catcher.service.dao",
        "org.springframework.data.jpa.convert.threeten"
})
public class DaoConfiguration {}
