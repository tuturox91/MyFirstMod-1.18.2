package net.sniklz.firstmod.item.custom.client;

import net.minecraft.resources.ResourceLocation;
import net.sniklz.firstmod.FirstMod;
import net.sniklz.firstmod.block.entity.AnimatedBlockEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AnimatedBlockItemModel extends AnimatedGeoModel<AnimatedBlockItem> {

    @Override
    public ResourceLocation getModelLocation(AnimatedBlockItem object) {
        return new ResourceLocation(FirstMod.MOD_ID, "geo/animated_block_geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AnimatedBlockItem object) {
        return new ResourceLocation(FirstMod.MOD_ID, "textures/block/animated_block.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AnimatedBlockItem animatable) {
        return new ResourceLocation(FirstMod.MOD_ID, "animations/animated_block.animation.json");
    }
}
