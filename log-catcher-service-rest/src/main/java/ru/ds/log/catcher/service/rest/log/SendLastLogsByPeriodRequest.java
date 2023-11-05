package ru.ds.log.catcher.service.rest.log;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendLastLogsByPeriodRequest {

    List<LogDto> logs;
}
