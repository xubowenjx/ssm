package com.xbw.spring.applications.i18n; 

import java.util.Locale;

import org.springframework.context.support.ResourceBundleMessageSource;

public class MessageSourceHelper {
    private ResourceBundleMessageSource messageSource;
    public  static Locale local;
    public String getMessage(String code, Object[] args,  Locale locale) {
    	locale=locale==null?local:locale;
        String msg = messageSource.getMessage(code, args,locale);
        return msg != null ? msg.trim() : msg;
    }
   
    public void setMessageSource(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
    