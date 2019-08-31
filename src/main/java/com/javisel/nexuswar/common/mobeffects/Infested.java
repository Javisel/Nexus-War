package com.javisel.nexuswar.common.mobeffects;


import com.javisel.nexuswar.main.utilities.MobUtilities;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySilverfish;

public class Infested extends MobEffect {

	public Infested() {
		super(true, 192 * 192 * 192, "infested");
	}

	@Override
	public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {

		if (entityLivingBaseIn.getActivePotionEffect(this).getDuration() %2==0 ) {

			if (!entityLivingBaseIn.getEntityWorld().isRemote) {

				for (int i =0; i<1+amplifier;i++) {
					MobUtilities.spawnCreature(entityLivingBaseIn.getEntityWorld(),
							new EntitySilverfish(entityLivingBaseIn.world), entityLivingBaseIn.posX,
							entityLivingBaseIn.posY, entityLivingBaseIn.posZ);
				}
			}

		}
	}

	@Override

	public boolean isReady(int duration, int amplifier) {

		int j = 25 >> amplifier;

		if (j > 0) {
			return duration % j == 0;
		} else {
			return true;
		}
	}

}
