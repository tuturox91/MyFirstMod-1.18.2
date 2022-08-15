package net.sniklz.firstmod.modRegistries;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.sniklz.firstmod.item.ModItems;

public class ModCreativeModeTab {
    public static final CreativeModeTab FIRSTMOD_TAB = new CreativeModeTab("firstmodtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.VAPATITE.get());
        }
    };
}
