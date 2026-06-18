package com.foxinerd.mercury_madness.block;

import com.foxinerd.mercury_madness.mobeffect.MMMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.level.Level;
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
            AreaEffectCloud poisonCloud = new AreaEffectCloud((Level) level, pos.getX(), pos.getY(), pos.getZ());
            poisonCloud.setRadius(2.0f);
            poisonCloud.setDuration(100);
            poisonCloud.setCustomParticle(ParticleTypes.NOXIOUS_GAS);
            poisonCloud.addEffect(new MobEffectInstance(MMMobEffects.HYDRARGYRIA, 3 * 20, 0));
            level.addFreshEntity(poisonCloud);
            level.setBlock(pos, MMBlocks.MERCURY.defaultBlockState(), 1);
        }
    }
}
