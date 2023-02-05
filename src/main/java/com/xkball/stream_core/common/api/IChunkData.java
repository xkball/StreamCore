package com.xkball.stream_core.common.api;

import net.minecraft.nbt.NBTTagCompound;

public interface IChunkData {
    
    NBTTagCompound getChunkData();
    
    void setChunkData(NBTTagCompound nbtTagCompound);
    
}
