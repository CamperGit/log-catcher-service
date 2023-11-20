package ru.ds.log.catcher.service.core.system;

import ru.ds.log.catcher.service.dao.entity.system.SystemEntity;

import java.util.List;

public interface SystemService {

    SystemEntity getByNameOrCreate(String name);

    SystemEntity save(SystemEntity system);

    List<SystemEntity> findAll();

    SystemEntity findById(Long id);

    boolean existsById(Long id);
}
