package com.xkball.stream_core.common.block;

import com.xkball.stream_core.StreamCore;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

public class CustomBlockBase {
    
    private static final Class<Block> blockClass = Block.class;
    private static final Method setSoundType;
    
    static {
        try {
            setSoundType = blockClass.getDeclaredMethod("setSoundType", SoundType.class);
            setSoundType.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    
    private final BlockType blockType;
    private final BlockProperty blockProperty;
    
    private final String id;
    
    public CustomBlockBase(BlockType blockType, BlockProperty blockProperty,String id) {
        this.blockType = blockType;
        this.blockProperty = blockProperty;
        this.id = id;
    }
    
    public Supplier<Block> toBlock(){
        return () -> {
            try {
                blockProperty.build();
                Block block = new Block(blockProperty.getBlockMaterial()){
                    @SuppressWarnings("deprecation")
                    @Override
                    public boolean isFullCube(IBlockState state) {
                        return blockProperty.isTranslucent();
                    }
    
                    @SuppressWarnings("deprecation")
                    @Override
                    public boolean isOpaqueCube(IBlockState state) {
                        return blockProperty.isTranslucent();
                    }
    
                    @SuppressWarnings("deprecation")
                    @Override
                    public boolean isTranslucent(IBlockState state) {
                        return blockProperty.isTranslucent();
                    }
                };
                block.setRegistryName(StreamCore.MODID,id);
                if(blockProperty.isTranslucent()) {
                    block.setLightOpacity(0);
                    
                }
                
                if(blockProperty.getBlockHardness() < 0) block.setBlockUnbreakable();
                else block.setHardness(blockProperty.getBlockHardness());
                if(blockProperty.getRequiresTool() != null){
                    block.setHarvestLevel(blockProperty.getRequiresTool(), blockProperty.getHarvestLevel());
                }
                
                block.setCreativeTab(blockProperty.getDisplayOnCreativeTab());
                block.setLightLevel(blockProperty.getLightValue());
                block.setResistance(blockProperty.getBlockResistance());
                setSoundType.invoke(block,blockProperty.getBlockSoundType());
                
                return block;
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    public BlockProperty getBlockProperty() {
        return blockProperty;
    }
    
    public BlockType getBlockType() {
        return blockType;
    }
    
    
    public String getId() {
        return id;
    }
}
