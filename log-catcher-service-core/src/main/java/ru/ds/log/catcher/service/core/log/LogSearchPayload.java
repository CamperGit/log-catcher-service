package ru.ds.log.catcher.service.core.log;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Sort;
import ru.ds.log.catcher.service.dao.entity.log.LogType;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogSearchPayload {

    List<LogType> typeIn;

    Long systemId;

    int pageNumber;

    int pageSize;

    Sort.Direction sortDirection;

    String sortProperty;
}
