package com.xkball.stream_core.common.chunk;

import com.xkball.stream_core.common.api.IChunkData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.chunk.Chunk;

@SuppressWarnings("unused")
public class ChunkUtil {
    
    public static NBTTagCompound getChunkNbt(Chunk chunk){
        return ((IChunkData)chunk).getChunkData();
    }
    
    public static NBTTagCompound readNBT(Chunk chunk,String key){
        IChunkData data = (IChunkData) chunk;
        return data.getChunkData().getCompoundTag(key);
    }
    
    public static NBTTagCompound readNBT(Chunk chunk,String key,NBTTagCompound defaultValue){
        IChunkData data = (IChunkData) chunk;
        return data.getChunkData().hasKey(key) ? data.getChunkData().getCompoundTag(key) : defaultValue;
    }
    
    
    public static void setNBT(Chunk chunk,String key,NBTTagCompound tag){
        IChunkData data = (IChunkData) chunk;
        NBTTagCompound taga = data.getChunkData();
        taga.setTag(key,tag);
        data.setChunkData(tag);
    }
    
    public static int readInt(Chunk chunk,String key){
        IChunkData data = (IChunkData) chunk;
        return data.getChunkData().getInteger(key);
    }
    
    public static int readInt(Chunk chunk,String key,int defaultValue){
        IChunkData data = (IChunkData) chunk;
        return data.getChunkData().hasKey(key) ? data.getChunkData().getInteger(key) : defaultValue;
    }
    
    public static void setInt(Chunk chunk,String key,int value){
        IChunkData data = (IChunkData) chunk;
        NBTTagCompound tag = data.getChunkData();
        tag.setInteger(key,value);
        data.setChunkData(tag);
    }
    
    public static void addInt(Chunk chunk,String key,int addValue){
        IChunkData data = (IChunkData) chunk;
        NBTTagCompound tag = data.getChunkData();
        int buff = data.getChunkData().getInteger(key);
        tag.setInteger(key,buff+addValue);
        data.setChunkData(tag);
    }
    
    public static boolean readBoolean(Chunk chunk,String key){
        IChunkData data = (IChunkData) chunk;
        return data.getChunkData().getBoolean(key);
    }
    
    public static void setBoolean(Chunk chunk,String key,boolean value){
        IChunkData data = (IChunkData) chunk;
        NBTTagCompound tag = data.getChunkData();
        tag.setBoolean(key,value);
        data.setChunkData(tag);
    }
    public static long readLong(Chunk chunk,String key){
        IChunkData data = (IChunkData) chunk;
        return data.getChunkData().getLong(key);
    }
    
    public static void setLong(Chunk chunk,String key,long value){
        IChunkData data = (IChunkData) chunk;
        NBTTagCompound tag = data.getChunkData();
        tag.setLong(key,value);
        data.setChunkData(tag);
    }
    
    public static String readString(Chunk chunk,String key){
        IChunkData data = (IChunkData) chunk;
        return data.getChunkData().getString(key);
    }
    
    public static void setString(Chunk chunk,String key,String value){
        IChunkData data = (IChunkData) chunk;
        NBTTagCompound tag = data.getChunkData();
        tag.setString(key,value);
        data.setChunkData(tag);
    }
}
