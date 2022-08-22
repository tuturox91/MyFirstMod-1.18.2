package net.sniklz.firstmod.events;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BlockLogBreak {
    @SubscribeEvent
    public  void harvestChek(PlayerEvent.BreakSpeed event) {
        //System.out.println("Im in");
        BlockState block = event.getState();

        if (event.getState().is(BlockTags.create(new ResourceLocation("minecraft:logs"))))
        {
            //System.out.println("In event");
            Player player = event.getPlayer();
            ItemStack item = player.getItemBySlot(EquipmentSlot.MAINHAND);
            if (!(item.getItem() instanceof AxeItem))
            {
                event.setCanceled(true);
            }
        }
    }
}
