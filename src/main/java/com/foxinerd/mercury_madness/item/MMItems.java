package com.foxinerd.mercury_madness.item;

import com.foxinerd.mercury_madness.block.MMBlocks;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.CreativeModeTabs;

public class MMItems {

    public static void registerModItems() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register((creativeTab) -> {
            creativeTab.accept(MMBlocks.POTENT_CINNABAR.asItem());
        });
    }
}
