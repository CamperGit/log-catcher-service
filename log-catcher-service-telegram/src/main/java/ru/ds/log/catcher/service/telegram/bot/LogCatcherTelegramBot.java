package ru.ds.log.catcher.service.telegram.bot;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.ds.log.catcher.service.telegram.property.TelegramBotProperties;

@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LogCatcherTelegramBot extends TelegramLongPollingBot {

    TelegramBotProperties telegramBotProperties;

    public LogCatcherTelegramBot(TelegramBotProperties telegramBotProperties) {
        super(new DefaultBotOptions());
        this.telegramBotProperties = telegramBotProperties;
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return telegramBotProperties.getUsername();
    }

    @Override
    public String getBotToken() {
        return telegramBotProperties.getToken();
    }
}
