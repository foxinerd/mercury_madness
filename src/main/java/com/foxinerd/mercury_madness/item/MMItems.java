package com.foxinerd.mercury_madness.item;

import com.foxinerd.mercury_madness.MMConstants;
import com.foxinerd.mercury_madness.block.MMBlocks;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemIds;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Function;

import static net.minecraft.sounds.SoundEvents.BUCKET_EMPTY_POWDER_SNOW;

public class MMItems {
   public static final Item MERCURY_BUCKET = register("mercury_bucket", settings -> new SolidBucketItem(MMBlocks.MERCURY, net.minecraft.sounds.SoundEvents.BUCKET_EMPTY_POWDER_SNOW, settings),(new Item.Properties()).stacksTo(1).useItemDescriptionPrefix().craftRemainder(Items.BUCKET));
    public static <GenericItem extends Item> GenericItem register(String name, Function<Item.Properties, GenericItem> itemFactory, Item.Properties settings) {
        // Create the item key.
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, MMConstants.id(name));

        // Create the item instance.
        GenericItem item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    // make a copy of the register method that takes a string and an item instance
    // but instead of taking a factory and properties, just make it take the item instance directly


    public static void registerModItems() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register((creativeTab) -> {
            creativeTab.accept(MMBlocks.POTENT_CINNABAR.asItem());
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(((itemGroup) -> itemGroup.accept(MMItems.MERCURY_BUCKET)));
    }
}
