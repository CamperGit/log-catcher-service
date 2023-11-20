package ru.ds.log.catcher.service.telegram.handler.command;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Список констант команд, обрабатываемых телеграмм сообщений
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum CommandValue {

    SELECT_SYSTEM("/select_system"),
    READ_LAST_LOGS("/read_last_logs"),
    READ_LAST_ERROR_LOGS("/read_last_error_logs");

    String value;
}
