package com.javisel.nexuswar.common.network;

import com.javisel.nexuswar.common.entitycapabilities.NexusWarCapabilitiesProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class NexusMessage implements IMessage {
    public NexusMessage(){}



    public double getMana() {
        return mana;
    }

    public double getMaxMana() {
        return maxMana;
    }

    public double getMPS() {
        return mps;
    }

    private double mana;
    private double maxMana;
    private double mps;

    public NexusMessage(EntityPlayer player) {


        mana= player.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY, null).getMana();
        maxMana= player.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY, null).getMaxMana();
        mps =  player.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY, null).getMPS();

    }

    @Override public void toBytes(ByteBuf buf) {
        buf.writeDouble(mana);
        buf.writeDouble(maxMana);
        buf.writeDouble(mps);



    }

    @Override
    public void  fromBytes(ByteBuf buf) {

        mana=buf.readDouble();
        maxMana=buf.readDouble();
        mps=buf.readDouble();
    }
}
