package com.xkball.stream_core.common.chunk;

import com.xkball.stream_core.common.api.IChunkData;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ChunkEventHandler {
    
    public static final String NBTName = "scChunkNBT";
    
    @SubscribeEvent
    public static void onChunkDataLoading(ChunkDataEvent.Load event){
        Chunk chunk = event.getChunk();
        NBTTagCompound tag = event.getData();
        ((IChunkData) chunk).setChunkData(tag.getCompoundTag(NBTName));
    }
    
    @SubscribeEvent
    public static void onChunkDataUnloading(ChunkDataEvent.Save event){
        Chunk chunk = event.getChunk();
        NBTTagCompound tag = event.getData();
        tag.setTag(NBTName,((IChunkData)chunk).getChunkData() == null ? new NBTTagCompound() : ((IChunkData)chunk).getChunkData());
    }
    
    @SubscribeEvent
    public static void on2(BlockEvent.BreakEvent event){
        Chunk chunk = event.getWorld().getChunkFromBlockCoords(event.getPos());
        IChunkData data = (IChunkData) chunk;
        NBTTagCompound tag = data.getChunkData();
        ChunkUtil.setLong(chunk,"BlockBroke",tag.getLong("BlockBroke")+1);
        if(event.getState().getBlock() == Blocks.STONE){
            World world = event.getWorld();
            event.getPlayer().sendMessage(
                    new TextComponentString(
                    Long.toString(
                            ChunkUtil.readLong(world.getChunkFromBlockCoords(event.getPos()),"BlockBroke"))));
        }
    }
    
//    @SubscribeEvent
//    public static void on3(LivingEntityUseItemEvent.Start event){
//        Item item = event.getItem().getItem();
//        if(item == Items.DIAMOND_SWORD){
//            Entity entity = event.getEntity();
//            BlockPos pos = entity.getPosition();
//            World world = entity.getEntityWorld();
//            entity.sendMessage(
//
//        }
//    }
}
