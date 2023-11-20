package ru.ds.log.catcher.service.telegram.provider;


import ru.ds.log.catcher.service.telegram.handler.command.BotCommandHandler;

public interface BotCommandHandlerProvider {

    BotCommandHandler getBotCommandHandler(String message);
}
