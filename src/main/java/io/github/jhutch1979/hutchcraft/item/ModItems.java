package io.github.jhutch1979.hutchcraft.item;

import io.github.jhutch1979.hutchcraft.HutchCraft;
import io.github.jhutch1979.hutchcraft.item.custom.CoalSliver;
import io.github.jhutch1979.hutchcraft.item.custom.DowsingRodItem;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HutchCraft.MOD_ID);

    public static final RegistryObject<Item> PULSATING_IRON_INGOT = ITEMS.register("pulsating_iron_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.HUTCH_CRAFT_TAB)));

    public static final RegistryObject<Item> PULSATING_IRON_NUGET = ITEMS.register("pulsating_iron_nuget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.HUTCH_CRAFT_TAB)));

    public static final RegistryObject<Item> DOWSING_ROD = ITEMS.register("dowsing_rod",
            () -> new DowsingRodItem(new Item.Properties().tab(ModCreativeModeTab.HUTCH_CRAFT_TAB)));

    public static final RegistryObject<Item> COAL_SLIVER = ITEMS.register("coal_sliver",
            () -> new CoalSliver(new Item.Properties().tab(ModCreativeModeTab.HUTCH_CRAFT_TAB)));

    public static final RegistryObject<Item> TURNIP = ITEMS.register("turnip",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.HUTCH_CRAFT_TAB).food(ModFoods.TURNIP)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
