package ru.ds.log.catcher.service.telegram.handler;

import ru.ds.log.catcher.service.telegram.handler.callback.CallbackFormat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface BotUtilService {

    default String wrapInBold(String text) {
        return String.format("<b>%s</b>", text);
    }

    default String wrapInItalic(String text) {
        return String.format("<i>%s</i>", text);
    }

    default String addWrapLine(String text) {
        return String.format("%s\n", text);
    }

    default String addWrapLine() {
        return "\n";
    }

    default Long getIdFromCallback(CallbackFormat format, String callback) {
        Pattern pattern = Pattern.compile(format.getIdRegex());
        Matcher matcher = pattern.matcher(callback);
        if (!matcher.find()) {
            throw new IllegalStateException(String.format("Not found id for data string %s", callback));
        }
        String group = matcher.group();
        String idString = group.replaceAll("[\\]\\[]", "");
        return Long.valueOf(idString);
    }
}
