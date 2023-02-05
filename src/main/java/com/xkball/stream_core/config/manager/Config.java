package com.xkball.stream_core.config.manager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
//所有被注解的字段应该是static的
public @interface Config {
    
    String modid() default "stream_core";
    
    Class<? extends ConfigLoader> loader();
    
    //文件路径 在config文件夹下 用/分割 最后一个为文件名 前面为文件夹
    String path() default "common";
    
    boolean canReload() default false;
    
    @interface SubLoaders{
        Class<? extends ConfigLoader>[] loaders();
    }
}
