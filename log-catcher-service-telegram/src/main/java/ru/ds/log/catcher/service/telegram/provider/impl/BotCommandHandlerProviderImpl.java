package ru.ds.log.catcher.service.telegram.provider.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.ds.log.catcher.service.telegram.handler.command.BotCommandHandler;
import ru.ds.log.catcher.service.telegram.handler.command.SelectSystemCommandHandler;
import ru.ds.log.catcher.service.telegram.provider.BotCommandHandlerProvider;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BotCommandHandlerProviderImpl implements BotCommandHandlerProvider {

    SelectSystemCommandHandler selectSystemCommandHandler;

    List<BotCommandHandler> commandHandlers;

    @Override
    public BotCommandHandler getBotCommandHandler(String message) {
        if (StringUtils.isEmpty(message)) {
            return selectSystemCommandHandler;
        }
        return commandHandlers.stream()
                .filter((handler) -> handler.isApplicable(message))
                .findFirst()
                .orElse(selectSystemCommandHandler);
    }
}
