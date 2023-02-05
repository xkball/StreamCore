package com.xkball.stream_core.config.manager;

import com.google.gson.JsonObject;

public interface ConfigLoader{
    
    JsonObject write(ConfigData data) throws IllegalAccessException;
    
    Object read(JsonObject json);
    
    
}
