package com.javisel.nexuswar.common.network;


import com.javisel.nexuswar.main.NexusWar;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class PacketHandler extends SimpleNetworkWrapper{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(NexusWar.MODID);

    public PacketHandler(String channelName) {
        super(channelName);
    }


}
