package com.xkball.stream_core.doc;

import com.xkball.stream_core.config.manager.ConfigLoader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Doc {
    
    String modid() default "stream_core";
    
    Class<? extends ConfigLoader<?>> loader();
    
    //文件路径 在doc/modid/文件夹下 用/分割 为文件夹名
    String path() default "common";
    
}
