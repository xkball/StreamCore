package com.xkball.stream_core.common.block;

import com.xkball.stream_core.StreamCore;
import com.xkball.stream_core.common.item.ItemReg;
import com.xkball.stream_core.config.SCConfigs;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = StreamCore.MODID)
public class BlockReg {
    
    public static final List<BlockDef<?>> blocks = new ArrayList<>();
    public static final List<BlockDef<?>> autoRegBlocks = new ArrayList<>();
    
    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        if(SCConfigs.enableAutoReg){
            for(CustomBlockBase blockBase : SCConfigs.autoRegBlocks){
                addAutoRegBlock(blockBase);
            }
        }
        for(BlockDef<?> blockDef : blocks) {
            event.getRegistry().register(blockDef.getBlock());
        }
    }

    
    
    public static <T extends Block> BlockDef<T> createBlock(String id, Supplier<T> supplier,boolean needItemBlock){
        T block = supplier.get();
        ResourceLocation resourceLocation = new ResourceLocation(StreamCore.MODID,id);
        ItemBlock itemBlock = new ItemBlock(block);
        //block.setRegistryName(resourceLocation);
        block.setUnlocalizedName(StreamCore.MODID+"."+id);
        itemBlock.setRegistryName(resourceLocation);
        itemBlock.setUnlocalizedName(StreamCore.MODID+"."+id);
        BlockDef<T> blockDef = new BlockDef<>(block,itemBlock);
        BlockReg.blocks.add(blockDef);
        if(needItemBlock) ItemReg.items.add(blockDef);
        return blockDef;
    }
    
    public static void addAutoRegBlock(CustomBlockBase blockBase){
        BlockDef<Block> blockDef = createBlock(blockBase.getId(),blockBase.toBlock(),true);
        BlockReg.autoRegBlocks.add(blockDef);
        ItemReg.autoRegItems.add(blockDef);
    }
    
}
