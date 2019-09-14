package com.javisel.nexuswar.main.utilities;

import com.javisel.nexuswar.common.entitycapabilities.INexusWarCapabilities;
import com.javisel.nexuswar.common.entitycapabilities.NexusWarCapabilitiesProvider;
import com.javisel.nexuswar.common.enums.EnumTeams;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class MobUtilities {

    public static Entity spawnCreature(World world, EntityLiving entitycreature, double x, double y, double z) {

        Entity entity = null;

        entity = entitycreature;
        EntityLiving entityliving = (EntityLiving) entity;
        entity.setLocationAndAngles(x, y, z, MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F);
        entityliving.rotationYawHead = entityliving.rotationYaw;
        entityliving.renderYawOffset = entityliving.rotationYaw;
        entityliving.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entityliving)),
                (IEntityLivingData) null);
        if (!world.isRemote) {

            world.spawnEntity(entity);

            entityliving.playLivingSound();

        }
        return entity;
    }

    public static boolean isOnSameTeam(Entity a, Entity b) {

        return a.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY,null).getTeam() == b.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY,null).getTeam();
    }

    public static boolean canHurt(Entity attacker, Entity defender) {

        if (defender.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY,null).getTeam() == EnumTeams.NEUTRAL || defender.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY,null).getTeam()==EnumTeams.UNAFFILIATED) return  true;

        else return  !isOnSameTeam(attacker,defender);



    }

    public static boolean isNexusCombat(Entity a, Entity b) {

        INexusWarCapabilities ACapabilities = a.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY,null);
        INexusWarCapabilities BCapabilities = b.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY,null);

        return (ACapabilities.getTeam() != EnumTeams.UNAFFILIATED && BCapabilities.getTeam()!=EnumTeams.UNAFFILIATED && ACapabilities.getTeam() != BCapabilities.getTeam());


    }


}
