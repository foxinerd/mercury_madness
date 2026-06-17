package com.foxinerd.mercury_madness.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class PotentCinnabarBlock extends Block {
    public PotentCinnabarBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void destroy(final LevelAccessor level, final BlockPos pos, final BlockState state) {
        if (!level.isClientSide()){
            level.setBlock(pos, MMBlocks.MERCURY.defaultBlockState(), 1);
        }
    }
}
