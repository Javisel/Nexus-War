package com.javisel.nexuswar.common.entitycapabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

import javax.annotation.Nullable;

public class NexusWarCapabilitiesStorage implements IStorage<INexusWarCapabilities> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<INexusWarCapabilities> capability, INexusWarCapabilities instance, EnumFacing side) {


        return instance.saveNBTData();


    }

    @Override
    public void readNBT(Capability<INexusWarCapabilities> capability, INexusWarCapabilities instance, EnumFacing side, NBTBase nbt) {
        if (nbt instanceof NBTTagCompound){

        }
    }
}