package com.foxinerd.mercury_madness.block;

import com.foxinerd.mercury_madness.item.MMItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.PowderSnowBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
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

    public static boolean canEntityWalkOnPowderSnow(final Entity entity) {
        return false;
    }

    protected void entityInside(final BlockState state, final Level level, final BlockPos pos, final Entity entity, final InsideBlockEffectApplier effectApplier, final boolean isPrecise) {
        if (!(entity instanceof LivingEntity) || entity.getInBlockState().is(this)) {
            entity.makeStuckInBlock(state, new Vec3((double)0.9F, (double)1.5F, (double)0.9F));
            if (level.isClientSide()) {
                RandomSource random = level.getRandom();
                boolean isMoving = entity.xOld != entity.getX() || entity.zOld != entity.getZ();
                if (isMoving && random.nextBoolean()) {
                    level.addParticle(ParticleTypes.SNOWFLAKE, entity.getX(), (double)(pos.getY() + 1), entity.getZ(), (double)(Mth.randomBetween(random, -1.0F, 1.0F) * 0.083333336F), (double)0.05F, (double)(Mth.randomBetween(random, -1.0F, 1.0F) * 0.083333336F));
                }
            }
        }

        BlockPos position = pos.immutable();

        if ((entity instanceof LivingEntity)) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 5 * 20, 0), null);
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.POISON, 5 * 20, 0), null);
        }
    }


}
