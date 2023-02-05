package com.xkball.stream_core.config.loader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.xkball.stream_core.config.manager.ConfigData;
import com.xkball.stream_core.config.manager.ConfigLoader;

public class BasicConfigLoaders {
    
    public static class BooleanConfigLoader implements ConfigLoader {
    
        @Override
        public JsonObject write(ConfigData data) throws IllegalAccessException {
            Object value = data.getFieldValue();
            JsonObject result = new JsonObject();
            result.addProperty("boolean",(boolean)value);
            return result;
        }
    
        @Override
        public Object read(JsonObject json) {
            try {
                return json.get("boolean").getAsBoolean();
            } catch (Exception e){
                throw new JsonParseException(e);
            }
         
        }
    }
    
    public static class StringConfigLoader implements ConfigLoader{
    
        @Override
        public JsonObject write(ConfigData data) throws IllegalAccessException {
            String value = (String) data.getFieldValue();
            JsonObject result = new JsonObject();
            result.addProperty("string",value);
            return result;
        }
    
        @Override
        public Object read(JsonObject json) {
            return json.get("string").getAsString();
        }
    }
    
    public static class BlockTypeConfigLoader implements ConfigLoader{
    
        @Override
        public JsonObject write(ConfigData data) throws IllegalAccessException {
            return null;
        }
    
        @Override
        public Object read(JsonObject json) {
            return null;
        }
    }
}
