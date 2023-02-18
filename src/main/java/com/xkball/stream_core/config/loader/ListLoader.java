package com.xkball.stream_core.config.loader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xkball.stream_core.config.manager.ConfigLoader;

import java.util.ArrayList;
import java.util.List;

public class ListLoader<T> implements ConfigLoader<List<T>> {
    
    @Override
    @SuppressWarnings("unchecked")
    public JsonObject write(List<T> data, ConfigLoader<?>... subLoaders) throws IllegalAccessException {
        JsonArray array = new JsonArray();
        ConfigLoader<T> loader = (ConfigLoader<T>) subLoaders[0];
        for(T t : data){
            array.add(loader.write(t));
            
        }
        JsonObject result = new JsonObject();
        result.add("list",array);
        return result;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<T> read(JsonObject json, ConfigLoader<?>... subLoaders) {
        List<T> list = new ArrayList<>();
        ConfigLoader<T> loader = (ConfigLoader<T>) subLoaders[0];
        for(JsonElement jsonElement : json.get("list").getAsJsonArray()){
            list.add(loader.read(jsonElement.getAsJsonObject()));
        }
        return list;
    }
}
