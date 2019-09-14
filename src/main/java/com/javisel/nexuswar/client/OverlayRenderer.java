package com.javisel.nexuswar.client;

import com.javisel.nexuswar.common.entitycapabilities.INexusWarCapabilities;
import com.javisel.nexuswar.common.entitycapabilities.NexusWarCapabilitiesProvider;
import com.javisel.nexuswar.main.NexusWar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OverlayRenderer  extends Gui {
        private final ResourceLocation manabar = new ResourceLocation(NexusWar.MODID, "textures/gui/manabar.png");
        Minecraft minecraft = Minecraft.getMinecraft();
    private final int tex_width = 182, tex_height = 11, barwidth = 180, barheight = 8;

        @SubscribeEvent
    public void renderManaBar(RenderGameOverlayEvent event) {

            INexusWarCapabilities nexusWarCapabilities = minecraft.player.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY,null);

            if (nexusWarCapabilities.getMaxMana()>0 && nexusWarCapabilities.isUsingNexusInventory()) {



                float manaratio = (float)(nexusWarCapabilities.getMana() / nexusWarCapabilities.getMaxMana());
                int currentWidth = (int) (barwidth * manaratio);



                minecraft.renderEngine.bindTexture(manabar);

                drawTexturedModalRect(0, 0, 0, 0, tex_width, tex_height);
                drawTexturedModalRect(0, 0, 1, 0, currentWidth, barheight);

                String s = nexusWarCapabilities.getMana() + "/" + Math.round(nexusWarCapabilities.getMaxMana());
                drawCenteredString(minecraft.fontRenderer,s, 0,0 , Integer.parseInt("ffffff", 16));
                minecraft.renderEngine.bindTexture(Gui.ICONS);
            }

        }
}
