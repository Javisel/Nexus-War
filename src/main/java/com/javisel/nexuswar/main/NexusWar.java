package com.javisel.nexuswar.main;

import com.javisel.nexuswar.common.items.ModItems;
import com.javisel.nexuswar.common.mobeffects.ModMobEffects;
import com.javisel.nexuswar.main.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

@Mod(dependencies="required-after:ebwizardry", modid = NexusWar.MODID, name = NexusWar.NAME, version = NexusWar.VERSION)
public class NexusWar
{
    public static final String MODID = "nexus_war";
    public static final String NAME = "Nexus War";
    public static final String VERSION = "0.1";

    @SidedProxy(clientSide = "com.javisel.nexuswar.main.proxy.ClientProxy", serverSide = "com.javisel.nexuswar.main.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static NexusWar instance;

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);

    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);

    }
    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            ModItems.register(event.getRegistry());
          //  ModBlocks.registerItemBlocks(event.getRegistry());

        }
        @SubscribeEvent
        public static void RegisterPotions(RegistryEvent.Register<Potion> event) {
            ModMobEffects.register(event.getRegistry());

        }


        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) {
          ModItems.registerModels();
            //ModBlocks.registerModels();

        }

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
        //    ModBlocks.register(event.getRegistry());
        }
    }
}
