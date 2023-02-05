package com.xkball.stream_core.common.api;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IDynamicHardnessBlock {
    float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos);
}
