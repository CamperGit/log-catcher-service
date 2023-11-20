package ru.ds.log.catcher.service.telegram.handler.command;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.ds.log.catcher.service.core.bot.LocaleMessageSource;
import ru.ds.log.catcher.service.core.system.SystemService;
import ru.ds.log.catcher.service.core.user.UserService;
import ru.ds.log.catcher.service.dao.entity.system.SystemEntity;
import ru.ds.log.catcher.service.dao.entity.user.UserEntity;
import ru.ds.log.catcher.service.telegram.bot.LogCatcherTelegramBot;
import ru.ds.log.catcher.service.telegram.handler.callback.CallbackFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

import static java.util.Collections.singletonList;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SelectSystemCommandHandler implements BotCommandHandler {

    SystemService systemService;

    LocaleMessageSource localeMessageSource;

    static String SELECT_SYSTEM_MESSAGE_SOURCE = "system.list.select";

    static String SYSTEMS_EMPTY_MESSAGE_SOURCE = "system.list.empty";

    static int SYSTEMS_IN_ROW_LIMIT = 3;

    @Override
    public void handle(LogCatcherTelegramBot bot, Update update) throws TelegramApiException {
        Message message = update.getMessage();
        String chatId = message.getChatId().toString();
        List<SystemEntity> systems = systemService.findAll();
        SendMessage botMessage;
        if (CollectionUtils.isEmpty(systems)) {
            botMessage = new SendMessage(chatId, localeMessageSource.getMessage(SYSTEMS_EMPTY_MESSAGE_SOURCE));
        } else {
            botMessage = new SendMessage(chatId, wrapInBold(localeMessageSource.getMessage(SELECT_SYSTEM_MESSAGE_SOURCE)));
            botMessage.setReplyMarkup(getSystemsMarkup(systems));
        }
        botMessage.enableHtml(true);
        bot.execute(botMessage);
    }

    @Override
    public boolean isApplicable(String message) {
        return message.equals(CommandValue.SELECT_SYSTEM.getValue());
    }

    private InlineKeyboardMarkup getSystemsMarkup(List<SystemEntity> systems) {
        if (CollectionUtils.isEmpty(systems)) {
            return null;
        }

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<List<SystemEntity>> lists = splitSystems(systems);
        for (List<SystemEntity> systemsInRow : lists) {
            List<InlineKeyboardButton> currentRow = new ArrayList<>();
            for (SystemEntity systemEntity : systemsInRow) {
                InlineKeyboardButton systemButton = new InlineKeyboardButton(systemEntity.getName());
                systemButton.setCallbackData(String.format(CallbackFormat.SYSTEM_FORMAT.getValue(), systemEntity.getId()));
                currentRow.add(systemButton);
            }
            rowList.add(currentRow);
        }
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private List<List<SystemEntity>> splitSystems(List<SystemEntity> systems) {
        // add validation if needed
        return systems.stream()
                .collect(Collector.of(
                        ArrayList::new,
                        (accumulator, item) -> {
                            if(accumulator.isEmpty()) {
                                accumulator.add(new ArrayList<>(singletonList(item)));
                            } else {
                                List<SystemEntity> last = accumulator.get(accumulator.size() - 1);
                                if(last.size() == SYSTEMS_IN_ROW_LIMIT) {
                                    accumulator.add(new ArrayList<>(singletonList(item)));
                                } else {
                                    last.add(item);
                                }
                            }
                        },
                        (li1, li2) -> {
                            li1.addAll(li2);
                            return li1;
                        }
                ));
    }
}
