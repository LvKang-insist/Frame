package com.admin.annotations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Lv
 * Created at 2019/7/2
 */

@Target(ElementType.FIELD) //代表在类级别上才可以使用该注解
@Retention(RetentionPolicy.RUNTIME)
public @interface DynamicString {
    String value() default "";
}
