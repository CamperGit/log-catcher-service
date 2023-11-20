package ru.ds.log.catcher.service.telegram.exception;

public class LogCatcherChatBotTelegramException extends RuntimeException {

    public LogCatcherChatBotTelegramException() {
    }

    public LogCatcherChatBotTelegramException(String message) {
        super(message);
    }

    public LogCatcherChatBotTelegramException(String message, Throwable cause) {
        super(message, cause);
    }
}
