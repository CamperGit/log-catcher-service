package ru.ds.log.catcher.service.core.system.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.ds.log.catcher.service.core.system.SystemService;
import ru.ds.log.catcher.service.dao.entity.system.SystemEntity;
import ru.ds.log.catcher.service.dao.entity.system.SystemRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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

    @Override
    public List<SystemEntity> findAll() {
        return systemRepository.findAll();
    }

    @Override
    public SystemEntity findById(Long id) {
        return systemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Not found system for id = %d", id)
                ));
    }

    @Override
    public boolean existsById(Long id) {
        return systemRepository.existsById(id);
    }
}
