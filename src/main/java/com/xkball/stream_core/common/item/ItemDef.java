package com.xkball.stream_core.common.item;

import net.minecraft.item.Item;

public class ItemDef<T extends Item>{
   // private Supplier<T> supplier;
    private T item;
    
    public ItemDef(T item){
        this.item = item;
    }
    
    public Item getItem(){
        return item;
    }
    
}
