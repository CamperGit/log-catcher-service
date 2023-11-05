package ru.ds.log.catcher.service.core.log.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ds.log.catcher.service.core.log.LogService;
import ru.ds.log.catcher.service.dao.entity.log.LogEntity;
import ru.ds.log.catcher.service.dao.entity.log.LogRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LogServiceImpl implements LogService {

    LogRepository logRepository;

    @Override
    public LogEntity save(LogEntity log) {
        return logRepository.save(log);
    }

    @Override
    public List<LogEntity> saveAll(List<LogEntity> logs) {
        return logRepository.saveAll(logs);
    }
}
