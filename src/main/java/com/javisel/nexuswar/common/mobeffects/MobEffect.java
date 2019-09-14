package com.javisel.nexuswar.common.mobeffects;

import com.javisel.nexuswar.main.NexusWar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class MobEffect extends Potion {
    String textureid;
    protected MobEffect(boolean isBadEffectIn, int liquidColorIn,String name) {
        super(isBadEffectIn, liquidColorIn);
        this.textureid=name;
        setPotionName("effect."+name);
        setRegistryName(NexusWar.MODID, name);

        // TODO Auto-generated constructor stub
    }
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        mc.getTextureManager().bindTexture(new ResourceLocation(NexusWar.MODID, "textures/effect/" + textureid + ".png"));


        Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
    }

    @Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc, float alpha) {



        mc.getTextureManager().bindTexture(new ResourceLocation(NexusWar.MODID, "textures/effect/" + textureid + ".png"));

        Gui.drawModalRectWithCustomSizedTexture(x+3, y+3, 0, 0, 18, 18, 18, 18);

    }



}
