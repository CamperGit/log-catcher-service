package ru.ds.log.catcher.service.core.log.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.ds.log.catcher.service.core.log.LogTelegramService;
import ru.ds.log.catcher.service.dao.entity.log.LogEntity;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LogTelegramServiceImpl implements LogTelegramService {

    static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static String LOG_STRING_FORMAT = "%s %s %s\n";

    static int TELEGRAM_MESSAGE_LIMIT = 4096;

    @Override
    public List<String> getTextMessagesFromLogs(List<LogEntity> logs) {
        List<String> messages = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (LogEntity log : logs) {
            String logLine = getLogString(log);
            if ((builder.length() + logLine.length()) > TELEGRAM_MESSAGE_LIMIT) {
                messages.add(builder.toString());
                builder = new StringBuilder(logLine);
            } else {
                builder.append(logLine);
            }
        }
        String lastMessageString = builder.toString();
        if (!StringUtils.isEmpty(lastMessageString)) {
            messages.add(lastMessageString);
        }
        return messages;
    }

    @Override
    public String getLogString(LogEntity log) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return String.format(LOG_STRING_FORMAT, dateFormatter.format(log.getTime()), log.getType(), log.getMessage());
    }
}
