package ru.ds.log.catcher.service.telegram.handler.callback;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Формат данных для обработчиков колбеков телеграм сообщений
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum CallbackFormat {

    SYSTEM_FORMAT("system.[%d]", "system.\\[\\d{0,}\\]", "\\[\\d{0,}\\]");

    String value;
    String applicableRegex;
    String idRegex;
}
