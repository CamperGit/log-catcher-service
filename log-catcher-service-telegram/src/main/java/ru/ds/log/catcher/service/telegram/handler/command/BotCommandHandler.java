package ru.ds.log.catcher.service.telegram.handler.command;

import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.ds.log.catcher.service.telegram.bot.LogCatcherTelegramBot;
import ru.ds.log.catcher.service.telegram.handler.BotUtilService;

public interface BotCommandHandler extends BotUtilService {

    /**
     * Метод обработки команды
     *
     * @param bot    - бот
     * @param update - модель данных телеграм сообщения
     * @throws TelegramApiException
     */
    @Transactional
    void handle(LogCatcherTelegramBot bot, Update update) throws TelegramApiException;

    /**
     * Проверка на применимость обработчика
     *
     * @param message - команда
     * @return применим ли обработчик
     */
    boolean isApplicable(String message);
}
