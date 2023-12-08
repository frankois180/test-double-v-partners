package com.rickandmorty.infrastructure.config;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

public class CharacterMessage {
    public enum MsgName {

        INFO_API_NAME,
        DATA_ALREADY_API,
        TYPE_NOT_FOUND


    }

    private static MessageSourceAccessor accessor;

    static {
        accessor = initAccessor();
    }

    private static MessageSourceAccessor initAccessor() {

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return new MessageSourceAccessor(messageSource, Locale.forLanguageTag("es"));

    }

    public static String msg(MsgName name) {

        String asProperty = name.toString().toLowerCase().replace("_", ".");
        return accessor.getMessage(asProperty);

    }

    public static String msg(MsgName name, Object... params) {

        String asProperty = name.toString().toLowerCase().replace("_", ".");
        return accessor.getMessage(asProperty, params);

    }

    public static String apiName() {

        return msg(MsgName.INFO_API_NAME);

    }

}
