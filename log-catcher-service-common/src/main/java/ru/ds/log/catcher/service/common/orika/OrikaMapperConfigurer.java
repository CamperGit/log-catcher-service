package ru.ds.log.catcher.service.common.orika;

import ma.glasnost.orika.MapperFactory;

public interface OrikaMapperConfigurer {
    void configure(MapperFactory factory);
}
