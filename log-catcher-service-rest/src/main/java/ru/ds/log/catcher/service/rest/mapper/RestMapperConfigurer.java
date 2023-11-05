package ru.ds.log.catcher.service.rest.mapper;

import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;
import ru.ds.log.catcher.service.common.orika.OrikaMapperConfigurer;
import ru.ds.log.catcher.service.dao.entity.log.LogEntity;
import ru.ds.log.catcher.service.dao.entity.system.SystemEntity;
import ru.ds.log.catcher.service.rest.log.LogDto;
import ru.ds.log.catcher.service.rest.system.SystemDto;

@Component
public class RestMapperConfigurer implements OrikaMapperConfigurer {

    @Override
    public void configure(MapperFactory factory) {
        factory.classMap(LogDto.class, LogEntity.class)
                .byDefault()
                .register();

        factory.classMap(SystemDto.class, SystemEntity.class)
                .byDefault()
                .register();
    }
}