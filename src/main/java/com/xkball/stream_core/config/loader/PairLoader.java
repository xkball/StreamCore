package com.xkball.stream_core.config.loader;

import com.google.gson.JsonObject;
import com.xkball.stream_core.config.manager.ConfigLoader;
import com.xkball.stream_core.utils.Pair;

public class PairLoader<K,V> implements ConfigLoader<Pair<K,V>> {
    
    @Override
    @SuppressWarnings("unchecked")
    public JsonObject write(Pair<K,V> data, ConfigLoader<?>... subLoaders) throws IllegalAccessException {
        ConfigLoader<K> keyLoader = (ConfigLoader<K>) subLoaders[0];
        ConfigLoader<V> valueLoader = (ConfigLoader<V>) subLoaders[1];
        JsonObject result = new JsonObject();
        result.add("key",keyLoader.write(data.getKey()));
        result.add("value",valueLoader.write(data.getValue()));
        return result;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public Pair<K,V> read(JsonObject json,ConfigLoader<?>... subLoaders) {
        ConfigLoader<K> keyLoader = (ConfigLoader<K>) subLoaders[0];
        ConfigLoader<V> valueLoader = (ConfigLoader<V>) subLoaders[1];
        K k = keyLoader.read(json.getAsJsonObject("key"));
        V v = valueLoader.read(json.getAsJsonObject("value"));
        return new Pair<>(k, v);
    }
}
