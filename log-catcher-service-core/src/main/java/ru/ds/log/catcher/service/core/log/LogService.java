package ru.ds.log.catcher.service.core.log;


import ru.ds.log.catcher.service.dao.entity.log.LogEntity;

import java.util.List;

public interface LogService {

    LogEntity save(LogEntity log);

    List<LogEntity> saveAll(List<LogEntity> logs);
}
