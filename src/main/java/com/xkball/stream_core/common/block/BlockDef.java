package com.xkball.stream_core.common.block;

import com.xkball.stream_core.common.item.ItemDef;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class BlockDef <T extends Block> extends ItemDef<ItemBlock> {
    //Supplier<T> blockSupplier;
    T block;
    
    public BlockDef(T block,ItemBlock itemBlock) {
        super(itemBlock);
        this.block = block;
    }
    
    public T getBlock(){
        return block;
    }
    
}
