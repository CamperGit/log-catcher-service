package ru.ds.log.catcher.service.rest.log;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ds.log.catcher.service.common.orika.DefaultMapper;
import ru.ds.log.catcher.service.common.orika.OrikaMapper;
import ru.ds.log.catcher.service.core.log.LogUploaderService;
import ru.ds.log.catcher.service.dao.entity.log.LogEntity;
import ru.ds.log.catcher.service.dao.entity.system.SystemEntity;
import ru.ds.log.catcher.service.rest.common.CommonResponse;

import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/v1/logs")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Api(tags = {"Контроллер для работы с логами"})
public class LogController {

    @DefaultMapper
    OrikaMapper mapper;

    LogUploaderService logUploaderService;

    @PostMapping("/upload")
    @ApiOperation("Загрузка логов")
    public ResponseEntity<CommonResponse> create(
            @ApiParam(value = "Запрос на загрузку логов")
            @RequestBody SendLastLogsByPeriodRequest request
    ) {
        SystemEntity system = mapper.map(request.getSystem(), SystemEntity.class);
        List<LogEntity> logs = mapper.mapAsList(request.getLogs(), LogEntity.class);
        logUploaderService.uploadLogsForSystem(system, logs);
        return ResponseEntity.ok(CommonResponse.builder().build());
    }
}
