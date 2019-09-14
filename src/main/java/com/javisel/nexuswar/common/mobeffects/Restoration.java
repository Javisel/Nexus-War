package com.javisel.nexuswar.common.mobeffects;

import com.javisel.nexuswar.common.entitycapabilities.INexusWarCapabilities;
import com.javisel.nexuswar.common.entitycapabilities.NexusWarCapabilities;
import com.javisel.nexuswar.common.entitycapabilities.NexusWarCapabilitiesProvider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class Restoration extends MobEffect {

	public Restoration() {
		super(false, 37631, "restoration");
		// TODO Auto-generated constructor stub
	}

	
	@Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier)

    {
    	if (entityLivingBaseIn instanceof  EntityPlayer) {
    		EntityPlayer player = (EntityPlayer) entityLivingBaseIn;
			INexusWarCapabilities nexusWarCapabilities = player.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY,null);

			nexusWarCapabilities.setMana(nexusWarCapabilities.getMana() + (nexusWarCapabilities.getMaxMana() * 0.01+(+0.01*amplifier)));
		}

    }
	@Override
	 public boolean isReady(int duration, int amplifier)
	    {
	       
	         
	            
	                return duration % 20 == 0;
	         
	     
	    }
	    }       
	        

