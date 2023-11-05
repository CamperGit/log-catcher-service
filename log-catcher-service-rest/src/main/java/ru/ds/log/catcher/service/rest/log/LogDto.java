package ru.ds.log.catcher.service.rest.log;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.ds.log.catcher.service.core.log.LogType;
import ru.ds.log.catcher.service.rest.system.SystemDto;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogDto {

    Long id;

    LocalDateTime time;

    LogType type;

    String clazz;

    String thread;

    String message;

    SystemDto system;
}
