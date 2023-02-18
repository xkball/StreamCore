package com.xkball.stream_core.config.loader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.xkball.stream_core.config.manager.ConfigLoader;
import com.xkball.stream_core.utils.CollectionUtils;
import com.xkball.stream_core.utils.Pair;

import java.util.HashMap;
import java.util.Map;

public class MapConfigLoader<K, V> implements ConfigLoader<Map<K,V>> {
    @Override
    public JsonObject write(Map<K,V> data,ConfigLoader<?>... subLoaders) {
        JsonObject result = new JsonObject();
        try {
            PairLoader<K,V> loader = new PairLoader<>();
            for(Map.Entry<K,V> entry : data.entrySet()){
                Pair<K,V> pair = CollectionUtils.formEntryToPair(entry);
                result.add("map", loader.write(pair,subLoaders));
            }
        } catch (IllegalAccessException e) {
            throw new JsonSyntaxException(e);
        }
        return result;
    }
    
    
    @Override
    public Map<K,V> read(JsonObject json,ConfigLoader<?>... subLoaders) {
        PairLoader<K,V> loader = new PairLoader<>();
        JsonArray array = json.getAsJsonArray("map");
        Map<K,V> result = new HashMap<>();
        for(JsonElement jsonE : array){
            JsonObject object = jsonE.getAsJsonObject();
            CollectionUtils.putIntoMap(result,loader.read(object));
        }
        return result;
    }
    
   
}
