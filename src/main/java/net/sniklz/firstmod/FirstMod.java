package net.sniklz.firstmod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.sniklz.firstmod.block.ModBlocks;
import net.sniklz.firstmod.block.entity.ModBlockEntities;
import net.sniklz.firstmod.events.BlockLogBreak;
import net.sniklz.firstmod.gui.ModMenuTypes;
import net.sniklz.firstmod.gui.VapatiteBlasterScreen;
import net.sniklz.firstmod.item.ModItems;
import net.sniklz.firstmod.sound.ModSounds;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FirstMod.MOD_ID)
public class FirstMod
{
    public static final String MOD_ID = "firstmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public FirstMod()
    {
        // Register the setup method for modloading
        IEventBus eventBus =  FMLJavaModLoadingContext.get().getModEventBus();



        ModItems.registerItems(eventBus);
        ModBlocks.registerBlocks(eventBus);
        ModBlockEntities.registerEntity(eventBus);
        ModSounds.registerSounds(eventBus);
        ModMenuTypes.registerMenu(eventBus);


        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new BlockLogBreak());


    }
    private void clientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(ModMenuTypes.VAPATITE_BLASTER_MENU.get(), VapatiteBlasterScreen::new);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

    }
}
