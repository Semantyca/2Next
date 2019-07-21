package com.toonext.scriptprocessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Command {
    String name();

    Trigger trigger() default Trigger.ONLY_CONSOLE;

}
