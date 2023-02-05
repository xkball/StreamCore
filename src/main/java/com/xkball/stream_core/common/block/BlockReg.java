package com.xkball.stream_core.common.block;

import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BlockReg {
    
    private final List<BlockDef<?>> blocks = new ArrayList<>();
    private final List<BlockDef<?>> autoRegBlocks = new ArrayList<>();
    
    
    public static class BlockDef<T extends Block>{
        Supplier<T> blockSupplier;
        
    }
}
