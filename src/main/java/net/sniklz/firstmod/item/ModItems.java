package net.sniklz.firstmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sniklz.firstmod.FirstMod;
import net.sniklz.firstmod.modRegistries.ItemRegister;
import net.sniklz.firstmod.modRegistries.ModCreativeModeTab;
import net.sniklz.firstmod.sound.ModSounds;


public class ModItems {


    public static final RegistryObject<Item> VAPATITE = ItemRegister.ITEMS.register("vapatite",() -> new Item(new Item.Properties().tab(ModCreativeModeTab.FIRSTMOD_TAB)));

    public static final RegistryObject<Item> PRAWDA_VIKA_MUSIC_DISC = ItemRegister.ITEMS.register("prawda_vika_disc",
            ()-> new RecordItem(4, ModSounds.PRAWDA_VIKA, new Item.Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(1)));


    public static void registerItems(IEventBus eventBus) {
        ItemRegister.ITEMS.register(eventBus);
    }





}
