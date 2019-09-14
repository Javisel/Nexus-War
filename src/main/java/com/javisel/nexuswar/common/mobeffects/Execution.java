package com.javisel.nexuswar.common.mobeffects;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class Execution extends MobEffect{
    protected Execution() {
        super(true, 00000,"execution");
    }

    @Override
    public boolean isReady(int duration, int amplifier) {

        return duration==0;
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {

        entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, entityLivingBaseIn.getMaxHealth()*10);
    }
}
