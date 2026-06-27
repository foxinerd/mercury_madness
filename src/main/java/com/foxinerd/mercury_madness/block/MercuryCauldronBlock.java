package com.foxinerd.mercury_madness.block;

import com.foxinerd.mercury_madness.mobeffect.MMMobEffects;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteractions;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.InsideBlockEffectType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LavaCauldronBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MercuryCauldronBlock extends AbstractCauldronBlock {
    public static final MapCodec<LavaCauldronBlock> CODEC = simpleCodec(LavaCauldronBlock::new);
    private static final VoxelShape SHAPE_INSIDE = Block.column((double)12.0F, (double)4.0F, (double)15.0F);
    private static final VoxelShape FILLED_SHAPE= Shapes.or(AbstractCauldronBlock.SHAPE, SHAPE_INSIDE);;

    public MapCodec<LavaCauldronBlock> codec() {
        return CODEC;
    }
    public MercuryCauldronBlock(final BlockBehaviour.Properties properties) {
        super(properties, MMCauldronInteractions.MERCURY);
    }

    protected double getContentHeight(final BlockState state) {
        return (double)0.9375F;
    }

    public boolean isFull(final BlockState state) {
        return true;
    }

    protected VoxelShape getEntityInsideCollisionShape(final BlockState state, final BlockGetter level, final BlockPos pos, final Entity entity) {
        return FILLED_SHAPE;
    }

    protected void entityInside(final BlockState state, final Level level, final BlockPos pos, final Entity entity, final InsideBlockEffectApplier effectApplier, final boolean isPrecise) {
        if ((entity instanceof LivingEntity)) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 3 * 20, 0), null);
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MMMobEffects.HYDRARGYRIA, 5 * 20, 0), null);
        }
    }
}
