package ru.ds.log.catcher.service.core.log;


import org.springframework.data.domain.Page;
import ru.ds.log.catcher.service.dao.entity.log.LogEntity;

import java.util.List;

public interface LogService {

    LogEntity save(LogEntity log);

    List<LogEntity> saveAll(List<LogEntity> logs);

    Page<LogEntity> find(LogSearchPayload logSearchPayload);
}
