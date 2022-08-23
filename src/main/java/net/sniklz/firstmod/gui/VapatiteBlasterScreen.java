package net.sniklz.firstmod.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.sniklz.firstmod.FirstMod;

public class VapatiteBlasterScreen extends AbstractContainerScreen<VapatiteBlasterMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FirstMod.MOD_ID, "textures/gui/vapatite_blaster_gui.png");

    public VapatiteBlasterScreen(VapatiteBlasterMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width- imageWidth) / 2 ;
        int y = (height - imageHeight) /2;

        this.blit(pPoseStack,x,y,0,0,imageWidth,imageHeight);

        if(menu.isCrafting()) {
            blit(pPoseStack, x+84, y+22, 176,14,menu.getScaledPrgoress(), 36);
        }

        if(menu.hasFuel()) {
            blit(pPoseStack, x+18, y+33 + 14 -menu.getScaledFuelProgress(), 176,
                    14- menu.getScaledFuelProgress(), 16, menu.getScaledFuelProgress());
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, delta);
        renderTooltip(pPoseStack,pMouseX,pMouseY);
    }
}
