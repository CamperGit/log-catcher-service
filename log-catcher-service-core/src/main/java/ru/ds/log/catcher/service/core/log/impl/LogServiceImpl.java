package ru.ds.log.catcher.service.core.log.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ru.ds.log.catcher.service.core.log.LogSearchPayload;
import ru.ds.log.catcher.service.core.log.LogService;
import ru.ds.log.catcher.service.dao.entity.log.LogEntity;
import ru.ds.log.catcher.service.dao.entity.log.LogRepository;
import ru.ds.log.catcher.service.dao.specification.Specifications;

import java.util.Arrays;
import java.util.List;

import static ru.ds.log.catcher.service.dao.specification.Specifications.equalOrReturnNull;
import static ru.ds.log.catcher.service.dao.specification.Specifications.inOrReturnNull;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LogServiceImpl implements LogService {

    LogRepository logRepository;

    static String ID = "id";

    @Override
    public LogEntity save(LogEntity log) {
        return logRepository.save(log);
    }

    @Override
    public List<LogEntity> saveAll(List<LogEntity> logs) {
        return logRepository.saveAll(logs);
    }

    @Override
    public Page<LogEntity> find(LogSearchPayload searchPayload) {
        Sort.Direction sortDirection = searchPayload.getSortDirection();
        String sortProperty = searchPayload.getSortProperty();
        int pageNumber = searchPayload.getPageNumber();
        int pageSize = searchPayload.getPageSize();

        List<Specification<LogEntity>> specifications = Arrays.asList(
                inOrReturnNull("type", searchPayload.getTypeIn()),
                equalOrReturnNull("system.id", searchPayload.getSystemId())
        );

        return logRepository.findAll(
                Specifications.And.<LogEntity>builder()
                        .specifications(specifications)
                        .build(),
                sortDirection == null || StringUtils.isEmpty(sortProperty)
                        ? PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, ID)
                        : PageRequest.of(pageNumber, pageSize, sortDirection, sortProperty));
    }
}
