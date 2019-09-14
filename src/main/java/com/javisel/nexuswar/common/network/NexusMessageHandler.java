package com.javisel.nexuswar.common.network;

import com.javisel.nexuswar.common.entitycapabilities.INexusWarCapabilities;
import com.javisel.nexuswar.common.entitycapabilities.NexusWarCapabilitiesProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class NexusMessageHandler implements IMessageHandler<NexusMessage, IMessage> {
    @Override
    public IMessage onMessage(NexusMessage message, MessageContext ctx) {
        EntityPlayerSP mc = Minecraft.getMinecraft().player;

        if (mc != null) {
            INexusWarCapabilities nexusWarCapabilities = mc.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY, null);
            nexusWarCapabilities.setMana(message.getMana());
            nexusWarCapabilities.setMaxMana(message.getMaxMana());
            nexusWarCapabilities.setMPS(message.getMPS());

        }
        return null;
    }

}
