package com.javisel.nexuswar.common.entitycapabilities;

import com.javisel.nexuswar.common.classes.framework.NexusClass;
import com.javisel.nexuswar.common.enums.EnumTeams;
import net.minecraft.nbt.NBTTagCompound;

public class NexusWarCapabilities implements INexusWarCapabilities{

    int level = 0;
    EnumTeams team = EnumTeams.NEUTRAL;
    double exp = 0;
    NexusClass nexusclass;
    double mana;
    double maxMana;
    double MPS;
    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int amount) {
        level=amount;
    }

    @Override
    public EnumTeams getTeam() {
        return team;
    }

    @Override
    public void setTeam(EnumTeams team) {
        this.team=team;
    }

    @Override
    public double getEXP() {
        return exp;
    }

    @Override
    public void setEXP(double xp) {
exp=xp;
    }

    @Override
    public NBTTagCompound saveNBTData() {
        NBTTagCompound baseTag = new NBTTagCompound();


        return baseTag;
    }

    @Override
    public NexusClass playerclass() {
        return nexusclass;
    }

    @Override
    public void setNexusClass(NexusClass nexusClass) {
nexusclass=nexusClass;
    }

    @Override
    public double getMana() {
        return mana;
    }

    @Override
    public double getMaxMana() {
        return maxMana;
    }

    @Override
    public double getMPS() {
        return MPS;
    }

    @Override
    public void setMana(double amount) {
            mana=amount;
    }

    @Override
    public void setMaxMana(double amount) {
            maxMana=amount;
    }

    @Override
    public void setMPS(double amount) {
        MPS=amount;
    }
}
