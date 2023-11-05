package ru.ds.log.catcher.service.core.system.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.ds.log.catcher.service.core.system.SystemService;
import ru.ds.log.catcher.service.dao.entity.system.SystemEntity;
import ru.ds.log.catcher.service.dao.entity.system.SystemRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SystemServiceImpl implements SystemService {

    SystemRepository systemRepository;

    @Override
    public SystemEntity getByNameOrCreate(String name) {
        return systemRepository.findByName(name).orElseGet(() -> save(new SystemEntity(null, name)));
    }

    @Override
    public SystemEntity save(SystemEntity system) {
        return systemRepository.save(system);
    }
}
