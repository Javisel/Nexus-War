package com.javisel.nexuswar.common.mobeffects;

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
		

    }
	@Override
	 public boolean isReady(int duration, int amplifier)
	    {
	       
	         
	            
	                return duration % 40 == 0;
	         
	     
	    }
	    }       
	        

