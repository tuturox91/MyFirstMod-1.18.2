package net.sniklz.firstmod.gui;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sniklz.firstmod.FirstMod;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, FirstMod.MOD_ID);

    public static final RegistryObject<MenuType<VapatiteBlasterMenu>> VAPATITE_BLASTER_MENU = registerMenuType(VapatiteBlasterMenu::new, "vapatite_blaster_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return  MENUS.register(name, () -> IForgeMenuType.create(factory));
    }


    public static void registerMenu(IEventBus eventBus) {
        MENUS.register(eventBus);
    }

}
