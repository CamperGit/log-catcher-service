package ru.ds.log.catcher.service.rest.system;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.ds.log.catcher.service.core.log.LogType;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SystemDto {

    Long id;

    String name;
}
