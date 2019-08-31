package com.javisel.nexuswar.main.proxy;


import com.javisel.nexuswar.common.classes.warrior.ClassItem;
import com.javisel.nexuswar.main.NexusWar;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
    }



    @Override
    public void postInit(FMLPostInitializationEvent e) {


    }




    @Override
    public void registerItemRenderer(Item item, int meta, String id) {


            ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(NexusWar.MODID + ":" + id, "inventory"));

    }




}