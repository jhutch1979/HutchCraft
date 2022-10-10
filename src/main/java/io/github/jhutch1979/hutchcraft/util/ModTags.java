package io.github.jhutch1979.hutchcraft.util;

import io.github.jhutch1979.hutchcraft.HutchCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> DOWSING_ROD_VALUABLES =
                tag("dowsing_rod_valuables");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(HutchCraft.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {

        public static final TagKey<Item>  PULSATING_IRON_INGOT = forgeTag("ingots/pulsating_iron");
        public static final TagKey<Item>  PULSATING_IRON_NUGET = forgeTag("nuggets/pulsating_iron");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(HutchCraft.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }

    }
}
