package com.javisel.nexuswar.common.mobeffects;

import com.javisel.nexuswar.common.mobeffects.HeavyHit;
import com.javisel.nexuswar.main.NexusWar;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraftforge.registries.IForgeRegistry;

public class ModMobEffects  {

    public static Potion Antiheal = new Antiheal().setPotionName("effect.antiheal").setRegistryName(NexusWar.MODID, "antiheal");
    public static Potion Corrosion = new Corrosion().setPotionName("effect.corrosion").setRegistryName(NexusWar.MODID, "corrosion");
    public static Potion Infested = new Infested().setPotionName("effect.infested").setRegistryName(NexusWar.MODID,"infested");
    public static Potion Disarm = new Disarm().setPotionName("effect.disarm").setRegistryName(NexusWar.MODID,"Disarm");
    public static Potion Reaping = new Reaping().setPotionName("effect.reaping").setRegistryName(NexusWar.MODID,"reaping");
    public static Potion Restoration = new Restoration().setPotionName("effect.restoration").setRegistryName(NexusWar.MODID,"restoration");
    public static Potion HeavyHit = new HeavyHit().setPotionName("effect.heavyhit").setRegistryName(NexusWar.MODID,"heavyhit");
    public static Potion Stun = new Stun().setPotionName("effect.stun").setRegistryName(NexusWar.MODID,"stun").	registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "0c8c7776-2e38-4997-9824-94d836be6d45", -55555555555555D, 2);
    public static Potion Execution = new Execution().setPotionName("effect.execution").setRegistryName(NexusWar.MODID,"execution");

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
                Execution
        );

    }
}
