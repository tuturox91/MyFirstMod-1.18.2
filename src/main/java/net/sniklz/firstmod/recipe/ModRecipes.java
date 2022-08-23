package net.sniklz.firstmod.recipe;

import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sniklz.firstmod.FirstMod;


public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, FirstMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<VapatiteBlasterRecipe>> VAPATITE_BLASTER_SERIALIZER =
            SERIALIZERS.register("vapatite_blasting", () -> VapatiteBlasterRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        Registry.register(Registry.RECIPE_TYPE, VapatiteBlasterRecipe.Type.ID, VapatiteBlasterRecipe.Type.INSTANCE);
    }

}
