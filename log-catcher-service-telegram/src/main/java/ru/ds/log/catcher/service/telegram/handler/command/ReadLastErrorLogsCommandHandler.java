package ru.ds.log.catcher.service.telegram.handler.command;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.ds.log.catcher.service.core.bot.LocaleMessageSource;
import ru.ds.log.catcher.service.core.log.LogSearchPayload;
import ru.ds.log.catcher.service.core.log.LogService;
import ru.ds.log.catcher.service.core.log.LogTelegramService;
import ru.ds.log.catcher.service.core.system.SystemService;
import ru.ds.log.catcher.service.core.user.UserService;
import ru.ds.log.catcher.service.dao.entity.log.LogEntity;
import ru.ds.log.catcher.service.dao.entity.log.LogType;
import ru.ds.log.catcher.service.dao.entity.system.SystemEntity;
import ru.ds.log.catcher.service.dao.entity.user.UserEntity;
import ru.ds.log.catcher.service.dao.entity.user.UserSettings;
import ru.ds.log.catcher.service.telegram.bot.LogCatcherTelegramBot;
import ru.ds.log.catcher.service.telegram.handler.callback.CallbackFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

import static java.util.Collections.singletonList;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReadLastErrorLogsCommandHandler implements BotCommandHandler {

    LogService logService;

    UserService userService;

    LocaleMessageSource localeMessageSource;

    LogTelegramService logTelegramService;

    static String LOGS_EMPTY_MESSAGE_SOURCE = "logs.empty";

    static int PAGE_NUMBER = 0;

    static int DEFAULT_PAGE_SIZE = 100;

    @Override
    public void handle(LogCatcherTelegramBot bot, Update update) throws TelegramApiException {
        Message message = update.getMessage();
        String chatId = message.getChatId().toString();
        UserEntity user = userService.findByTelegramChatId(chatId);
        Long systemId = Optional.ofNullable(user.getSettings())
                .map(UserSettings::getCurrentSystemId)
                .orElseThrow(() -> new IllegalStateException(
                        String.format("Current user with chat id = %s dont have selected system", chatId)));


        Page<LogEntity> logEntities = logService.find(LogSearchPayload.builder()
                .systemId(systemId)
                .pageNumber(PAGE_NUMBER)
                .pageSize(DEFAULT_PAGE_SIZE)
                .build());
        SendMessage botMessage;
        List<LogEntity> content = logEntities.getContent();
        if (CollectionUtils.isEmpty(content)) {
            botMessage = new SendMessage(chatId, localeMessageSource.getMessage(LOGS_EMPTY_MESSAGE_SOURCE));
            botMessage.enableHtml(true);
            bot.execute(botMessage);
        } else {
            List<String> textMessagesFromLogs = logTelegramService.getTextMessagesFromLogs(content);
            for (String textMessagesFromLog : textMessagesFromLogs) {
                bot.execute(new SendMessage(chatId, textMessagesFromLog));
            }
        }

    }

    @Override
    public boolean isApplicable(String message) {
        return message.equals(CommandValue.READ_LAST_LOGS.getValue());
    }
}
