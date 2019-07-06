package com.admin.core.util.annotation_util;

import com.admin.annotations.annotations.DynamicString;

import java.lang.reflect.Field;

/**
 * @author Lv
 * Created at 2019/7/2
 */
public class DynamicUtil {
    public static void inject(Object object){
        for(Field field :object.getClass().getDeclaredFields()){
            //判断该注解是否是 DynamicString 注解
            if (field.isAnnotationPresent(DynamicString.class)){
                //拿到注解
                DynamicString annotation = field.getAnnotation(DynamicString.class);
                //拿到值
                String value = annotation.value();
                if (value == null){
                    value = "";
                }


                //插入值
                try {
                    boolean accessible = field.isAccessible();
                    field.setAccessible(true);
                    field.set(object,value);
                    field.setAccessible(accessible);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
