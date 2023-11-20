package ru.ds.log.catcher.service.telegram.handler.callback;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.ds.log.catcher.service.core.bot.LocaleMessageSource;
import ru.ds.log.catcher.service.core.system.SystemService;
import ru.ds.log.catcher.service.core.user.UserService;
import ru.ds.log.catcher.service.dao.entity.system.SystemEntity;
import ru.ds.log.catcher.service.dao.entity.user.UserEntity;
import ru.ds.log.catcher.service.telegram.bot.LogCatcherTelegramBot;
import ru.ds.log.catcher.service.telegram.exception.LogCatcherChatBotTelegramException;

import java.util.regex.Pattern;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SystemSelectedCallbackHandler implements BotCallbackHandler {

    static String SYSTEM_SUCCESSFULLY_SELECTED_MESSAGE_SOURCE = "system.select.success";
    static String SYSTEM_SELECTED_ERROR_MESSAGE_SOURCE = "system.select.error";

    LocaleMessageSource localeMessageSource;
    UserService userService;
    SystemService systemService;

    @Override
    public void handle(LogCatcherTelegramBot bot, Update update) throws TelegramApiException {
        SendMessage sendMessage;
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        try {
            UserEntity user = userService.findByTelegramChatId(chatId);
            Long systemId = getIdFromCallback(CallbackFormat.SYSTEM_FORMAT, update.getCallbackQuery().getData());
            if (!systemService.existsById(systemId)) {
                throw new LogCatcherChatBotTelegramException(String.format("Not found system for id = %d", systemId));
            }
            SystemEntity system = systemService.findById(systemId);
            user.getSettings().setCurrentSystemId(systemId);
            userService.save(user);
            sendMessage = new SendMessage(chatId, String.format(localeMessageSource.getMessage(SYSTEM_SUCCESSFULLY_SELECTED_MESSAGE_SOURCE), wrapInBold(system.getName())));
        } catch (Exception e) {
            log.error("Error while trying to set current system for user", e);
            sendMessage = new SendMessage(chatId, localeMessageSource.getMessage(SYSTEM_SELECTED_ERROR_MESSAGE_SOURCE));
        }
        sendMessage.enableHtml(true);
        bot.execute(sendMessage);
    }

    @Override
    public boolean isApplicable(String callback) {
        return Pattern.compile(CallbackFormat.SYSTEM_FORMAT.getApplicableRegex()).matcher(callback).find();
    }
}
