package io.github.jhutch1979.hutchcraft.item;

import io.github.jhutch1979.hutchcraft.HutchCraft;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {

    public static final CreativeModeTab HUTCH_CRAFT_TAB = new CreativeModeTab("hutchcraftmodtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.PULSATING_IRON_INGOT.get());
        }
    };
}
