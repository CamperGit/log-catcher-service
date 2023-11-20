package ru.ds.log.catcher.service.telegram.provider;


import ru.ds.log.catcher.service.telegram.handler.callback.BotCallbackHandler;

public interface BotCallbackHandlerProvider {

    BotCallbackHandler getBotCallbackHandler(String callback);
}
