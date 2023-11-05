package ru.ds.log.catcher.service.core.system;

import ru.ds.log.catcher.service.dao.entity.system.SystemEntity;

public interface SystemService {


    SystemEntity getByNameOrCreate(String name);

    SystemEntity save(SystemEntity system);
}
