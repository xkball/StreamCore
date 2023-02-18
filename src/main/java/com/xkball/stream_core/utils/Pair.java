package com.xkball.stream_core.utils;

public class Pair<K,V> {
    
    final K key;
    final V value;
    
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public K getKey() {
        return key;
    }
    
    public V getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return "key: "+key.toString()+" value: "+value.toString();
    }
}
