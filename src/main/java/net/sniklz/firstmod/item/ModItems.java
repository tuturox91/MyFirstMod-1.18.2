package net.sniklz.firstmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sniklz.firstmod.FirstMod;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FirstMod.MOD_ID);

    public static final RegistryObject<Item> VAPATITE = ITEMS.register("vapatite",() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));


    public static void registerItems(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }





}
