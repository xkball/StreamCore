package com.xkball.stream_core.config.manager;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.*;
import com.xkball.stream_core.utils.JsonUtils;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.discovery.ASMDataTable;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.function.Predicate;

public class ConfigManager {
    
    //相对路径,数据
    private static final Multimap<String,ConfigData> configMap = ArrayListMultimap.create();
    
    private static final Predicate<ConfigData> saveOrLoadAll = (data) -> true;
    
    public static void loadConfigs(ASMDataTable data){
        
        //汇总file-config对应关系
        for(ASMDataTable.ASMData asmData : data.getAll(Config.class.getName())){
            ConfigData configData = new ConfigData(asmData);
            if(!configData.isFieldValid()){
                throw new RuntimeException("Config field must be static.");
            }
            configMap.put(configData.toRelativePath(),configData);
        }
        
        for(ASMDataTable.ASMData asmData : data.getAll(Config.SubLoaders.class.getName())){
            for(ConfigData data1 : configMap.values()){
                if(asmData.getClassName().equals(data1.getData().getClassName()) &&
                        asmData.getObjectName().equals(data1.getData().getObjectName())){
                    data1.addAdvData(ConfigData.SUB_LOADERS,asmData);
                }
            }
        }
        
        //读取是否存在
        for(String path: configMap.keySet()){
            File file = ConfigData.toFile(path);
            //不存在-生成
            if(!file.exists()){
                genFile(path,file, saveOrLoadAll,WriteFileType.GEN);
            }
            //存在-读
            else {
                if(!load(path,file,saveOrLoadAll)){
                    //读取失败--复制 重生成
                    try {
                        Files.copy(file.toPath(),new File(file.getAbsolutePath()+".error").toPath());
                        FMLLog.log.error("can not read file {}",file);
                        Files.delete(file.toPath());
                    } catch (IOException e) {
                        FMLLog.log.error("can not copy error file {}",file);
                    }
                    genFile(path,file, saveOrLoadAll,WriteFileType.REGEN);
                }
                
            }
        }
        
    }
    
    private synchronized static void genFile(String relativePath, File file, Predicate<ConfigData> canSave,WriteFileType type){
        if(!file.exists()){
            try {
                //Files.createFile(file.toPath());
                if(!file.getParentFile().mkdirs() & !file.createNewFile()){
                    throw new IOException("Failed to create file");
                }
            } catch (IOException e) {
                FMLLog.log.fatal("Unable to create the config file {}",file);
                throw new RuntimeException(e);
            }
        }
        try {
            JsonElement jsonExisted = new JsonParser().parse(new String(Files.readAllBytes(file.toPath())));
            try (OutputStream out = Files.newOutputStream(file.toPath())
                 // Files.newOutputStream(file.toPath())
            ){
        
                JsonObject json = jsonExisted.isJsonNull() ? new JsonObject() : jsonExisted.getAsJsonObject();
                for(ConfigData data : configMap.get(relativePath)){
                    if(canSave.test(data)){
                        String name = data.getFieldName();
                        if(json.has(name)) json.remove(name);
                        json.add(name,data.writeTOJson());
                    }
                    else {
                        json.add(data.getFieldName(),json.remove(data.getFieldName()));
                    }
                }
                out.write(JsonUtils.jsonToString(json).getBytes());
                FMLLog.log.info(type.info,file);
            }
        } catch (JsonSyntaxException | ClassNotFoundException | IOException | IllegalAccessException |
                     InstantiationException e) {
                throw new RuntimeException(e);
            }
        
    }
    
    private synchronized static void genFile(String relativePath, Predicate<ConfigData> canSave,WriteFileType type){
        genFile(relativePath,ConfigData.toFile(relativePath),canSave,type);
    }
    
    private synchronized static boolean load(String path,File file,Predicate<ConfigData> canLoad){
        try {
            JsonObject json = (JsonObject) new JsonParser().parse(new String(Files.readAllBytes(file.toPath())));
            for(ConfigData data : configMap.get(path)){
                if(canLoad.test(data)) {
                    JsonObject jo = json.getAsJsonObject(data.getFieldName());
                    if(jo != null) {
                        try {
                            Object o = data.getLoader().read(jo,data.getSubLoaders().toArray(new ConfigLoader[0]));
                            data.setValue(o);
                        }catch (Exception e){
                            genFile(path,(data1 -> data1.equals(data)),WriteFileType.REGEN);
                        }
                    }
                    else {
                        genFile(path,(data1 -> data1.equals(data)),WriteFileType.GEN);
                    }
                }
            }
            return true;
        } catch (JsonParseException | ClassCastException | IOException e) {
            return false;
        }
        
    }
    private synchronized static boolean load(String path,Predicate<ConfigData> canLoad){
        return load(path,ConfigData.toFile(path),canLoad);
    }
    
    public static void save(ConfigData data,boolean needReload){
        genFile(data.toRelativePath(),data.getFile(),(data1 -> data1.equals(data)),WriteFileType.SAVE);
        if(needReload) load(data.toRelativePath(),(data1 -> data1.equals(data) && data.canReload()));
    }
    
    public static void save(String modid,boolean needReload){
        for(String relativePath : configMap.keySet()){
            if (relativePath.contains(modid)) {
                genFile(relativePath,(data -> modid.equals(data.getModid())),WriteFileType.SAVE);
                if(needReload) load(relativePath,(data -> data.getModid().equals(modid)));
            }
        }
    }
    
    public static void save(boolean needReload){
        for(String relativePath : configMap.keySet()){
            genFile(relativePath,saveOrLoadAll,WriteFileType.SAVE);
            if(needReload) load(relativePath,saveOrLoadAll);
        }
    }
    
    public static void reload(ConfigData data){
        load(data.toRelativePath(),(data1 -> data1.equals(data) && data.canReload()));
    }
    
    public static void reload(String modid){
        for(String relativePath : configMap.keySet()){
            if (relativePath.contains(modid)) {
                load(relativePath,(data -> data.getModid().equals(modid) && data.canReload()));
            }
        }
    }
    
    public static void reload(){
        for(String relativePath : configMap.keySet()){
            load(relativePath,ConfigData::canReload);
        }
    }
   
    
    
    private enum WriteFileType{
        GEN("gen config file {}"),
        SAVE("save config file {}"),
        REGEN("regen config file {}");
        final String info;
        
        WriteFileType(String info){
            this.info = info;
        }
        
    }
}
