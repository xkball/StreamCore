package com.xkball.stream_core.config.manager;

import com.google.gson.JsonObject;

public interface ConfigLoader<T>{
    
    JsonObject write(T data,ConfigLoader<?>... subLoaders) throws IllegalAccessException;
    
    T read(JsonObject json,ConfigLoader<?>... subLoaders);
    
    
}
