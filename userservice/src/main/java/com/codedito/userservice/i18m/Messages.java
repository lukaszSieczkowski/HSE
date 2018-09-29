package com.codedito.userservice.i18m;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Messages {
    private MessageSourceAccessor accessor;
    private final MessageSource messageSource;

    @Autowired
    public Messages(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @PostConstruct
    private void init() {

        accessor = new MessageSourceAccessor(messageSource, LocaleContextHolder.getLocale());
    }

    public String get(final String code) {
        return accessor.getMessage(code);
    }
}
