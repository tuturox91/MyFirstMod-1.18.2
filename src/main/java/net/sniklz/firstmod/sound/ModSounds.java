package net.sniklz.firstmod.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sniklz.firstmod.FirstMod;

public class ModSounds {
     public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FirstMod.MOD_ID);

    public static RegistryObject<SoundEvent> PRAWDA_VIKA = reghisterSoundEvent("prawda_vika");

     private static RegistryObject<SoundEvent> reghisterSoundEvent(String name) {
         return  SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(FirstMod.MOD_ID, name)));
     }


    public static void  registerSounds(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
