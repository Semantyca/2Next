package com.toonext.localization.validation;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import java.util.Locale;

public class ValidationMessageInterpolator extends ResourceBundleMessageInterpolator {
    private final Locale iLocale;


    public ValidationMessageInterpolator(Locale iLocale) {
            super(new PlatformResourceBundleLocator(ResourceBundleMessageInterpolator.USER_VALIDATION_MESSAGES));
            this.iLocale = iLocale;
        }

    @Override
       public String interpolate(String messageTemplate, Context context){
            if (iLocale == null)
                return super.interpolate(messageTemplate, context);
            else
                return super.interpolate(messageTemplate, context, iLocale);
        }

}

    /*
    login_is_null=Login cannot be empty
    user_not_found=User not found

    login_is_null=Логин не может быть пустым
    user_not_found=Пользователь не найден




     */