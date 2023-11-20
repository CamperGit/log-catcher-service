package ru.ds.log.catcher.service.core.log;

import ru.ds.log.catcher.service.dao.entity.log.LogEntity;

import java.util.List;

public interface LogTelegramService {

    List<String> getTextMessagesFromLogs(List<LogEntity> logs);

    String getLogString(LogEntity log);
}
