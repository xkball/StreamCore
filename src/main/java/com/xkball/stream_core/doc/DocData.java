package com.xkball.stream_core.doc;

import com.xkball.stream_core.config.manager.ConfigLoader;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import org.objectweb.asm.Type;

import java.io.File;
import java.lang.reflect.Field;

public class DocData {
    
    private final ASMDataTable.ASMData data;
    private ConfigLoader<?> loader;
    
    public DocData(ASMDataTable.ASMData data) {
        this.data = data;
    }
    
    public File getFile(){
        return new File(SCDocGenerator.docDir.getAbsoluteFile()+"/"+getPath()+"/"+getFieldName()+".json");
    }
    
    public String getPath(){
        String path = (String) data.getAnnotationInfo().get("path");
        return path == null ? "common":path;
    }
    
    public String getModid(){
        String modid = (String) data.getAnnotationInfo().get("modid");
        return modid == null ? "stream_core":modid;
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
    
    public ConfigLoader getLoader() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if(loader == null) {
            Class<?> configLoader = Class.forName(((Type) data.getAnnotationInfo().get("loader")).getClassName(), true, Loader.instance().getModClassLoader());
            loader =  (ConfigLoader<?>) configLoader.newInstance();
        }
        return loader;
    }
    
    public String getFieldName(){
        return data.getObjectName();
    }
    
    public Object getFieldValue() throws IllegalAccessException {
        return getField().get(null);
    }
    
    
    public ASMDataTable.ASMData getData() {
        return data;
    }
    
}
