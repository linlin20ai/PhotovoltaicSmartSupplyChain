package com.example.entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.function.Consumer;

/**
 * @author 林圣涛
 */
public interface BeanData {

    default <V> V asViewObject(Class<V> clazz, Consumer<V> consumer){
        V v= this.asViewObject(clazz);
        consumer.accept(v);
        return  v;
    }
    default  <V> V asViewObject(Class<V> clazz){
        try{
            Field[] fields = clazz.getDeclaredFields();
            Constructor<V> constructor=clazz.getConstructor();
            V v=constructor.newInstance();
            for (Field field : fields) {
                convert(field,v);
            }
            return v;
        }catch (ReflectiveOperationException exception){
            throw new RuntimeException(exception.getMessage());
        }
    }


    private void convert(Field field,Object vo){
        try{
            Field source=this.getClass().getDeclaredField(field.getName());
            field.setAccessible(true);
            source.setAccessible(true);
            field.set(vo,source.get(this));
        } catch (NoSuchFieldException| IllegalAccessException ignored) {
        }
    }

}
