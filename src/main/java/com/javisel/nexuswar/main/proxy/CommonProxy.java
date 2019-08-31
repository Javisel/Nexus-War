package com.javisel.nexuswar.main.proxy;

import com.javisel.nexuswar.common.entitycapabilities.CapabilityHandler;
import com.javisel.nexuswar.common.entitycapabilities.INexusWarCapabilities;
import com.javisel.nexuswar.common.entitycapabilities.NexusWarCapabilities;
import com.javisel.nexuswar.common.entitycapabilities.NexusWarCapabilitiesStorage;
import com.javisel.nexuswar.main.EventHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {

    }

    public void init(FMLInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        CapabilityManager.INSTANCE.register(INexusWarCapabilities.class, new NexusWarCapabilitiesStorage(), NexusWarCapabilities.class);
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());

    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {


    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {


    }

    public void registerItemRenderer(Item item, int meta, String id) {

    }
}