package com.xkball.stream_core.common.item;

import com.xkball.stream_core.StreamCore;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = StreamCore.MODID)
public class ItemReg {
    public static final ArrayList<ItemDef<?>> items = new ArrayList<>();
    public static final ArrayList<ItemDef<?>> autoRegItems = new ArrayList<>();
    
    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event){
        IForgeRegistry<Item> registry = event.getRegistry();
        for(ItemDef<?> itemDef : items){
            registry.register(itemDef.getItem());
        }
    }
    
    @SubscribeEvent
    public static void onModelReg(ModelRegistryEvent event) {
        for(ItemDef<?> itemDef : items){
            ModelLoader.setCustomModelResourceLocation(itemDef.getItem(), 0,
                    new ModelResourceLocation(Objects.requireNonNull(itemDef.getItem().getRegistryName()), "inventory"));
    
        }
    }
}
