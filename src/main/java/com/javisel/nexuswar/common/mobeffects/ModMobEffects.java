package com.javisel.nexuswar.common.mobeffects;

import com.javisel.nexuswar.common.mobeffects.HeavyHit;
import com.javisel.nexuswar.main.NexusWar;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraftforge.registries.IForgeRegistry;

public class ModMobEffects  {

    public static Potion Antiheal = new Antiheal();
    public static Potion Corrosion = new Corrosion();
    public static Potion Infested = new Infested();
    public static Potion Disarm = new Disarm();
    public static Potion Reaping = new Reaping();
    public static Potion Restoration = new Restoration();
    public static Potion HeavyHit = new HeavyHit();
    public static Potion Stun = new Stun().	registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "0c8c7776-2e38-4997-9824-94d836be6d45", -55555555555555D, 2);
    public static Potion StunningStrike = new StunningStrike();
    public static Potion Execution = new Execution();
    public static Potion Invincibility = new Invincibility();
    public static void register(IForgeRegistry<Potion> registry) {

        registry.registerAll(
                Antiheal,
                Corrosion,
                Infested,
                Disarm,
                Reaping,
                Restoration,
                HeavyHit,
                Stun,
                StunningStrike,
                Execution,
                Invincibility
        );

    }
}
