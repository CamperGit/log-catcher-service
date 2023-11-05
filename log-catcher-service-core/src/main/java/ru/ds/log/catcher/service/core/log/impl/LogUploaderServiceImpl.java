package ru.ds.log.catcher.service.core.log.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.ds.log.catcher.service.core.log.LogService;
import ru.ds.log.catcher.service.core.log.LogUploaderService;
import ru.ds.log.catcher.service.core.system.SystemService;
import ru.ds.log.catcher.service.dao.entity.log.LogEntity;
import ru.ds.log.catcher.service.dao.entity.system.SystemEntity;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LogUploaderServiceImpl implements LogUploaderService {

    LogService logService;

    SystemService systemService;

    @Override
    public List<LogEntity> uploadLogsForSystem(SystemEntity system, List<LogEntity> logs) {
        SystemEntity systemFromDb = systemService.getByNameOrCreate(system.getName());
        logs.sort(Comparator.comparing(LogEntity::getTime));
        logs.forEach(log -> log.setSystem(systemFromDb));
        return logService.saveAll(logs);
    }
}
