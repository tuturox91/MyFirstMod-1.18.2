package net.sniklz.firstmod.events;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sniklz.firstmod.FirstMod;
import net.sniklz.firstmod.item.ModItems;

import java.util.Random;


@Mod.EventBusSubscriber(modid = FirstMod.MOD_ID)
public class CustomSwordEvent {
    private static int current_score = 0;
    private static double current_item_level;

    @SubscribeEvent
    public static void kill(LivingDeathEvent event) {
        if(!event.getEntity().level.isClientSide()) { //if someone die
            if(event.getSource().getDirectEntity() instanceof  Player) { //get player who killed
                Player player = ((Player) event.getSource().getDirectEntity()); //save player to variable
                if(player.getMainHandItem().getItem() == ModItems.TESTED_SWORD.get()) { //if player holds custom sword
                    ItemStack swordItem = player.getMainHandItem(); //save sword to variable
                    CompoundTag data = new CompoundTag(); //create tag?!
                    current_score = swordItem.getTag().getInt("sword_score"); //get current score

                    current_item_level = swordItem.getTag().getDouble("sword_level");//get sword level
                    //System.out.println("Prishlo: "+ current_item_level);
                    if(current_item_level == 0) current_item_level = 20;
                    //System.out.println("Chek na 0: " + current_item_level);

                    if(event.getEntity() instanceof Zombie) { //if this is zombie
                        current_score += 2; //add score
                    }
                    else if (event.getEntity() instanceof Skeleton || event.getEntity() instanceof Spider) { //if this is skeleton
                        current_score += 4; //add score
                    }
                    else if (event.getEntity() instanceof EnderMan || event.getEntity() instanceof Creeper) { //if this is EnderMan
                        current_score += 7; //add score
                    } else if (event.getEntity() instanceof Witch) {
                        current_score +=10;
                    }

                    if(current_score >= Math.floor(current_item_level)) { //player reward
                        //System.out.println("Nabili mobov:" + current_item_level);

                        current_item_level += current_item_level * 0.2;
                        //System.out.println( "Umnozili: " +current_item_level);
                        Random random = new Random();
                        int itemdrop = random.nextInt(0,100);
                        String name_of_drop = "";
                        if(itemdrop <= 60) {
                            name_of_drop = new ItemStack(ModItems.VAPATITE.get(), 2).getDisplayName().getString();
                            player.getInventory().add(new ItemStack(ModItems.VAPATITE.get(), 10)); //get reward
                        }
                        else if(itemdrop > 60 && itemdrop <= 90) {
                            name_of_drop = new ItemStack(Items.EMERALD, 1).getDisplayName().getString();
                            player.getInventory().add(new ItemStack(Items.EMERALD, 1)); //get reward
                        }
                        else if (itemdrop > 90) {
                            name_of_drop = new ItemStack(Items.DIAMOND, 1).getDisplayName().getString();
                            player.getInventory().add(new ItemStack(Items.DIAMOND , 1)); //get reward
                        }
                        player.sendMessage(new TranslatableComponent("tested_sword_award").append(": " + name_of_drop), player.getUUID()); //show text
                        current_score = 0; //reset sword score
                    }
                    data.putInt("sword_score", current_score); //put changed data
                    //System.out.println( "Kladem obratno: " +current_item_level);
                    data.putDouble("sword_level", current_item_level);
                    swordItem.setTag(data); // save changed data into sword
                }
            }
        }
    }
}
