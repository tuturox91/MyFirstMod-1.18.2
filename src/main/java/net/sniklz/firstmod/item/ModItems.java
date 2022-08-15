package net.sniklz.firstmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sniklz.firstmod.FirstMod;
import net.sniklz.firstmod.modRegistries.ItemRegister;
import net.sniklz.firstmod.modRegistries.ModCreativeModeTab;


public class ModItems {


    public static final RegistryObject<Item> VAPATITE = ItemRegister.ITEMS.register("vapatite",() -> new Item(new Item.Properties().tab(ModCreativeModeTab.FIRSTMOD_TAB)));


    public static void registerItems(IEventBus eventBus) {
        ItemRegister.ITEMS.register(eventBus);
    }





}
