package net.sniklz.firstmod.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sniklz.firstmod.FirstMod;
import net.sniklz.firstmod.item.ModItems;
import net.sniklz.firstmod.modRegistries.BlockRegister;
import net.sniklz.firstmod.modRegistries.ItemRegister;
import net.sniklz.firstmod.modRegistries.ModCreativeModeTab;

import java.util.function.Supplier;

public class ModBlocks {


    public static final RegistryObject<Block> VAPATITE_BLOCK = BlockRegister.registerBlock("vapatite_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeModeTab.FIRSTMOD_TAB);

    public static final RegistryObject<Block> VAPATITE_ORE = BlockRegister.registerBlock("vapatite_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeModeTab.FIRSTMOD_TAB);



    public static void registerBlocks(IEventBus eventBus) {
        BlockRegister.BLOCKS.register(eventBus);
    }
}
