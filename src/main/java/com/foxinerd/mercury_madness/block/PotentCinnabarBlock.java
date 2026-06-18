package com.foxinerd.mercury_madness.block;

import com.foxinerd.mercury_madness.MercuryMadness;
import com.foxinerd.mercury_madness.mobeffect.MMMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PotentCinnabarBlock extends Block {
    public PotentCinnabarBlock(Properties properties) {
        super(properties);
    }

    protected void spawnAfterBreak(final BlockState state, final ServerLevel level, final BlockPos pos, final ItemStack tool, final boolean dropExperience) {
        super.spawnAfterBreak(state, level, pos, tool, dropExperience);
        if ((Boolean)level.getGameRules().get(GameRules.BLOCK_DROPS) && !EnchantmentHelper.hasTag(tool, EnchantmentTags.PREVENTS_INFESTED_SPAWNS)) {
            this.DestructionConsequence(level, pos);
        }
    }

    public void DestructionConsequence (final LevelAccessor level, final BlockPos pos){
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
