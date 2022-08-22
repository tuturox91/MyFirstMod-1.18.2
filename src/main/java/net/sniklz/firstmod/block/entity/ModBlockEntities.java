package net.sniklz.firstmod.block.entity;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sniklz.firstmod.FirstMod;
import net.sniklz.firstmod.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, FirstMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<VapatiteBlaster_BlockEntity>> VAPATITE_BLASTER = BLOCK_ENTITIES.register("vapatite_blaster",
            () -> BlockEntityType.Builder.of(VapatiteBlaster_BlockEntity::new, ModBlocks.VAPATITE_BLASTER.get()).build(null));


    public static void registerEntity(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
