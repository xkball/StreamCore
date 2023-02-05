package com.xkball.stream_core.mixin;

import com.xkball.stream_core.common.api.IChunkData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Chunk.class)
public abstract class SCMixinChunk implements IChunkData {
    
    @Shadow public abstract void markDirty();
    
    @Shadow private boolean loaded;
    protected NBTTagCompound scChunkNBT;
    
    @Override
    public NBTTagCompound getChunkData() {
        return scChunkNBT;
    }
    
    @Override
    public void setChunkData(NBTTagCompound nbtTagCompound) {
        
        scChunkNBT = nbtTagCompound;
        if(this.loaded) this.markDirty();
    }
}
