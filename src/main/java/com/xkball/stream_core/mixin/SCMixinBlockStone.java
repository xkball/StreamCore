package com.xkball.stream_core.mixin;

import com.xkball.stream_core.common.api.IDynamicHardnessBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockStone.class)
public abstract class SCMixinBlockStone extends Block implements IDynamicHardnessBlock {
    
    
    public SCMixinBlockStone(Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
    }
    
    @Override
    public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos){
        int i = 0;
        for(EnumFacing facing : EnumFacing.values()){
            if(worldIn.getBlockState(pos.offset(facing)).getBlock() instanceof BlockStone){
                i++;
            }
        }
        return (float) (this.blockHardness*(0.9+i*0.1));
    }
}
