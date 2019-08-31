package com.javisel.nexuswar.common.entitycapabilities;

import com.javisel.nexuswar.common.classes.framework.NexusClass;
import com.javisel.nexuswar.common.enums.EnumTeams;
import net.minecraft.nbt.NBTTagCompound;

public interface INexusWarCapabilities {


    public int getLevel();
    public void setLevel(int amount);
    public EnumTeams getTeam();
    public void setTeam(EnumTeams team);
    public double getEXP();
    public void setEXP(double xp);
    public NexusClass playerclass();
    public void setNexusClass(NexusClass nexusClass);
    public double getMana();
    public double getMaxMana();
    public double getMPS();
    public void setMana(double amount);
    public void setMaxMana(double amount);
    public void setMPS(double amount);

    public NBTTagCompound saveNBTData();

}
