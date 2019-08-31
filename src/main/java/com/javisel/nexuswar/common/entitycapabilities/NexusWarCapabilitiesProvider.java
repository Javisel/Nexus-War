package com.javisel.nexuswar.common.entitycapabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class NexusWarCapabilitiesProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(INexusWarCapabilities.class)
    public static final Capability<INexusWarCapabilities> I_NEXUS_WAR_CAPABILITY = null;

    private INexusWarCapabilities instance = I_NEXUS_WAR_CAPABILITY.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == I_NEXUS_WAR_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == I_NEXUS_WAR_CAPABILITY ? I_NEXUS_WAR_CAPABILITY.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return I_NEXUS_WAR_CAPABILITY.getStorage().writeNBT(I_NEXUS_WAR_CAPABILITY, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        I_NEXUS_WAR_CAPABILITY.getStorage().readNBT(I_NEXUS_WAR_CAPABILITY, this.instance, null, nbt);
    }
}