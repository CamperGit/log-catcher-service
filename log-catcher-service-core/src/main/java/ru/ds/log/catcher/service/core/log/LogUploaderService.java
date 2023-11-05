package ru.ds.log.catcher.service.core.log;

import org.springframework.transaction.annotation.Transactional;
import ru.ds.log.catcher.service.dao.entity.log.LogEntity;
import ru.ds.log.catcher.service.dao.entity.system.SystemEntity;

import java.util.List;

public interface LogUploaderService {

    @Transactional
    List<LogEntity> uploadLogsForSystem(SystemEntity system, List<LogEntity> logs);
}
