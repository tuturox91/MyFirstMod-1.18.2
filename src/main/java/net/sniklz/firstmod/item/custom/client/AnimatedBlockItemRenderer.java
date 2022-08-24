package net.sniklz.firstmod.item.custom.client;

import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class AnimatedBlockItemRenderer extends GeoItemRenderer<AnimatedBlockItem> {

    public AnimatedBlockItemRenderer() {
        super(new AnimatedBlockItemModel());
    }
}
