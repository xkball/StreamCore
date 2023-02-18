package com.xkball.stream_core.config.loader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.xkball.stream_core.config.manager.ConfigLoader;

//仅限基础类和String
public class BasicConfigLoaders {
    
    public static class BooleanConfigLoader implements ConfigLoader<Boolean> {
    
        @Override
        public JsonObject write(Boolean value,ConfigLoader<?>... subLoaders) throws IllegalAccessException {
            JsonObject result = new JsonObject();
            result.addProperty("boolean",value);
            return result;
        }
    
        @Override
        public Boolean read(JsonObject json,ConfigLoader<?>... subLoaders) {
            try {
                return json.get("boolean").getAsBoolean();
            } catch (Exception e){
                throw new JsonParseException(e);
            }
         
        }
    }
    
    public static class StringConfigLoader implements ConfigLoader<String>{
    
        @Override
        public JsonObject write(String value,ConfigLoader<?>... subLoaders) throws IllegalAccessException {
            JsonObject result = new JsonObject();
            result.addProperty("string", value);
            return result;
        }
    
        @Override
        public String read(JsonObject json,ConfigLoader<?>... subLoaders) {
            return json.get("string").getAsString();
        }
    }
    
    public static class FloatConfigLoader implements ConfigLoader<Float>{
        @Override
        public JsonObject write(Float data, ConfigLoader<?>... subLoaders) throws IllegalAccessException {
            JsonObject result = new JsonObject();
            result.addProperty("float",data);
            return result;
        }
    
        @Override
        public Float read(JsonObject json, ConfigLoader<?>... subLoaders) {
            return json.get("float").getAsFloat();
        }
    }
    
}
