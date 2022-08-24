package net.sniklz.firstmod.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.sniklz.firstmod.modRegistries.BlockRegister;
import net.sniklz.firstmod.modRegistries.ModCreativeModeTab;

public class ModBlocks {


    public static final RegistryObject<Block> VAPATITE_BLOCK = BlockRegister.registerBlock("vapatite_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeModeTab.FIRSTMOD_TAB);

    public static final RegistryObject<Block> VAPATITE_ORE = BlockRegister.registerBlock("vapatite_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeModeTab.FIRSTMOD_TAB);

    public static final RegistryObject<Block> VAPATITE_BLASTER = BlockRegister.registerBlock("vapatite_blaster",
            () -> new BlasterBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()), ModCreativeModeTab.FIRSTMOD_TAB);

    public static final RegistryObject<Block> ANIMATED_BLOCK = BlockRegister.BLOCKS.register("animated_block",
            () -> new AnimatedBlock(BlockBehaviour.Properties.of(Material.STONE).noOcclusion()));

    public static void registerBlocks(IEventBus eventBus) {
        BlockRegister.BLOCKS.register(eventBus);
    }
}
