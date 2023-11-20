package ru.ds.log.catcher.service.core.bot.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.ds.log.catcher.service.core.bot.LocaleMessageSource;
import ru.ds.log.catcher.service.core.property.LocaleProperties;

import java.util.Locale;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LocaleMessageSourceImpl implements LocaleMessageSource {

    Locale locale;

    MessageSource messageSource;

    public LocaleMessageSourceImpl(LocaleProperties localeProperties, MessageSource messageSource) {
        this.locale = Locale.forLanguageTag(localeProperties.getTag());
        this.messageSource = messageSource;
    }

    public String getMessage(String key) {
        return messageSource.getMessage(key, null, locale);
    }
}
