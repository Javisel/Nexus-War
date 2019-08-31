package com.javisel.nexuswar.common.entitycapabilities;

import com.javisel.nexuswar.main.NexusWar;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler {


    public static final ResourceLocation NEXUSWARCAPABILITIES = new ResourceLocation(NexusWar.MODID, "EntityStageData");

    @net.minecraftforge.fml.common.eventhandler.SubscribeEvent
    public <SubscribeEvent> void attachCapability(AttachCapabilitiesEvent<Entity> event) {

        Entity ref = event.getObject();
        if (!(ref instanceof EntityLivingBase))
            return;

        else {

            event.addCapability(NEXUSWARCAPABILITIES, new NexusWarCapabilitiesProvider());



        }
    }


}
