package com.foxinerd.mercury_madness.block;

import com.foxinerd.mercury_madness.item.MMItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.PowderSnowBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public class MercuryBlock extends PowderSnowBlock implements BucketPickup {
    public MercuryBlock(Properties properties) {
        super(properties);
    }
    public ItemStack pickupBlock(final @Nullable LivingEntity user, final LevelAccessor level, final BlockPos pos, final BlockState state) {
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
        if (!level.isClientSide()) {
            level.levelEvent(2001, pos, Block.getId(state));
        }

        return new ItemStack(MMItems.MERCURY_BUCKET);
    }


}
