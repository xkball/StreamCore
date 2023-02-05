package com.xkball.stream_core.config.manager;

import com.google.gson.JsonObject;
import com.xkball.stream_core.StreamCore;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import org.objectweb.asm.Type;

import javax.annotation.Nonnull;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Objects;

public class ConfigData {
    @Nonnull
    private final ASMDataTable.ASMData data;
    private ASMDataTable.ASMData adv;
    
    public ConfigData(@Nonnull ASMDataTable.ASMData data) {
        this.data = data;
    }
    
    public void addAdvData(ASMDataTable.ASMData data){
        this.adv = data;
    }
    
    public boolean isFieldValid(){
        Field field = getField();
        int modifier = field.getModifiers();
        return Modifier.isStatic(modifier) && !Modifier.isFinal(modifier);
    }
    
    public Field getField(){
        try {
            Class<?> clazz = Class.forName(data.getClassName(),true, Loader.instance().getModClassLoader());
            String name = data.getObjectName();
            Field field = clazz.getField(name);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String getFieldName(){
        return data.getObjectName();
    }
    
    public Object getFieldValue() throws IllegalAccessException {
        return getField().get(null);
    }
    
    public String toRelativePath(){
        return getModid()+"/"+getPath()+".json";
    }
    
    public static File toFile(String relativePath){
        return new File(StreamCore.configDir.getPath()+"/"+relativePath);
    }
    
    public File getFile(){
        return toFile(toRelativePath());
    }
    
    public JsonObject writeTOJson() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return getLoader().write(this);
    }
    
   public void setValue(Object o) throws IllegalAccessException {
        getField().set(null,o);
   }
    
   //获得注解的成员
    public String getPath(){
        String path = (String) data.getAnnotationInfo().get("path");
        return path == null ? "common":path;
    }
    
    public String getModid(){
        String modid = (String) data.getAnnotationInfo().get("modid");
        return modid == null ? "stream_core":modid;
    }
    
    public boolean canReload(){
        Object b =  data.getAnnotationInfo().get("canReload");
        return b != null && (boolean) b;
    }
    
    public ConfigLoader getLoader() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> configLoader = Class.forName(((Type)data.getAnnotationInfo().get("loader")).getClassName(),true, Loader.instance().getModClassLoader());
        return (ConfigLoader) configLoader.newInstance();
    }
    
//    public ConfigLoader[] getSubLoaders(){
//        ArrayList<Class<? extends ConfigLoader>> classes = new ArrayList<>();
//        for
//    }
    
    @Nonnull
    public ASMDataTable.ASMData getData() {
        return data;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigData data1 = (ConfigData) o;
        return data.equals(data1.data);
    }
    
    @Override
    public String toString() {
        return data.toString();
    }
}