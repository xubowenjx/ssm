package com.xbw.spring.applications.i18n; 

import java.util.Locale;

import org.springframework.context.support.ResourceBundleMessageSource;

public class MessageSourceHelper {
    private ResourceBundleMessageSource messageSource;
    public String getMessage(String code, Object[] args,  Locale locale) {
    	Locale localen=locale==null?new Locale("en_US"):locale;
        String msg = messageSource.getMessage(code, args,localen);
        return msg != null ? msg.trim() : msg;
    }
   
    public void setMessageSource(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
    