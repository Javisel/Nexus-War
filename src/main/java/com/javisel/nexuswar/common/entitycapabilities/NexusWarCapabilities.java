package com.javisel.nexuswar.common.entitycapabilities;

import com.javisel.nexuswar.common.classes.framework.NexusClass;
import com.javisel.nexuswar.common.classes.wizard.WizardRank;
import com.javisel.nexuswar.common.enums.EnumTeams;
import electroblob.wizardry.constants.Element;
import electroblob.wizardry.spell.Spell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;

public class NexusWarCapabilities implements INexusWarCapabilities{

    int level = 0;
    EnumTeams team = EnumTeams.UNAFFILIATED;
    NexusClass nexusclass;
    NexusInventory nexusInventory;
    boolean isUsingNexusInventory = false;

    double exp = 0;

    ArrayList<Element> wizardElements;
    WizardRank wizardRank;
    double mana;
    double maxMana;
    double MPS;

  int   ticks = 0;

    @Override
    public ArrayList<Element> getWizardElements() {
        return wizardElements;
    }

    @Override
    public NBTTagCompound saveNBTData() {
        NBTTagCompound tag = new NBTTagCompound();

        if (team == EnumTeams.UNAFFILIATED) return  tag;
            tag.setInteger("level",level);
            tag.setString("team",team.toString());
            tag.setString("Class", nexusclass.toString());

            //Nexus Inventory Storage
            


            return tag;

    }


    @Override
    public void setWizardElement(Element element) {
        wizardElements.add(element);
    }

    @Override
    public WizardRank getWizardRank() {
        return wizardRank;
    }

    @Override
    public void setWizardRank(WizardRank wizardRank) {
        this.wizardRank=wizardRank;
    }

    @Override
    public int getLevel() {
        return level;
    }
    @Override
    public NexusInventory nexusInventory() {
        return nexusInventory;
    }

    @Override
    public boolean isUsingNexusInventory() {
        return isUsingNexusInventory;
    }

    @Override
    public void Tick() {

        ticks++;
        if (ticks==20) {
            ticks = 0;
            setMana(mana+MPS);
        }
    }
    @Override
    public void setIsUsingNexusInventory(boolean result) {
        isUsingNexusInventory=result;
    }

    @Override
    public void setNexusInventory(EntityPlayer player) {
        nexusInventory = new NexusInventory(player);
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
    public NexusClass getNexusClass() {
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
            if (mana>maxMana)mana=maxMana;
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
