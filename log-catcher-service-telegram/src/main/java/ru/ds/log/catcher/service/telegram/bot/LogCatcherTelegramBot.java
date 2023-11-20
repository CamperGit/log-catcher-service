package ru.ds.log.catcher.service.telegram.bot;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.ds.log.catcher.service.core.user.UserService;
import ru.ds.log.catcher.service.telegram.exception.LogCatcherChatBotTelegramException;
import ru.ds.log.catcher.service.telegram.handler.callback.BotCallbackHandler;
import ru.ds.log.catcher.service.telegram.handler.command.BotCommandHandler;
import ru.ds.log.catcher.service.telegram.property.TelegramBotProperties;
import ru.ds.log.catcher.service.telegram.provider.BotCallbackHandlerProvider;
import ru.ds.log.catcher.service.telegram.provider.BotCommandHandlerProvider;

@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LogCatcherTelegramBot extends TelegramLongPollingBot {

    TelegramBotProperties telegramBotProperties;

    UserService userService;

    BotCallbackHandlerProvider botCallbackHandlerProvider;

    BotCommandHandlerProvider botCommandHandlerProvider;

    public LogCatcherTelegramBot(TelegramBotProperties telegramBotProperties, UserService userService, BotCallbackHandlerProvider botCallbackHandlerProvider, BotCommandHandlerProvider botCommandHandlerProvider) {
        super(new DefaultBotOptions());
        this.telegramBotProperties = telegramBotProperties;
        this.userService = userService;
        this.botCallbackHandlerProvider = botCallbackHandlerProvider;
        this.botCommandHandlerProvider = botCommandHandlerProvider;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            createNewUserByUpdateIfAbsent(update);
            //CallbackQuery handling
            if (update.hasCallbackQuery() && !update.getCallbackQuery().getData().equals("null")) {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                String callback = callbackQuery.getData();
                BotCallbackHandler botCallbackHandler = botCallbackHandlerProvider.getBotCallbackHandler(callback);
                if (botCallbackHandler != null) {
                    botCallbackHandler.handle(this, update);
                }
            }

            //Commands and nonCommands messages handling
            Message message = update.getMessage();
            if (message != null && message.hasText()) {
                String text = message.getText();
                BotCommandHandler botCommandHandler = botCommandHandlerProvider.getBotCommandHandler(text);
                botCommandHandler.handle(this, update);
            }
        } catch (TelegramApiException e) {
            log.error("Commands and none commands handling exception", e);
            throw new LogCatcherChatBotTelegramException("Error while trying to handle telegram command or callback query", e);
        }
    }

    @Override
    public String getBotUsername() {
        return telegramBotProperties.getUsername();
    }

    @Override
    public String getBotToken() {
        return telegramBotProperties.getToken();
    }

    private void createNewUserByUpdateIfAbsent(Update update) {
        Message message;
        if (update.hasCallbackQuery() && !update.getCallbackQuery().getData().equals("null")) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            message = callbackQuery.getMessage();
        } else {
            message = update.getMessage();
        }
        String chatId = message.getChatId().toString();
        String username = message.getFrom().getUserName();
        userService.findByTelegramChatIdOrUsernameOrCreate(chatId, username);
    }
}
