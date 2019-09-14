package com.javisel.nexuswar.common.entitycapabilities;

import com.javisel.nexuswar.common.classes.framework.NexusClass;
import com.javisel.nexuswar.common.classes.wizard.WizardRank;
import com.javisel.nexuswar.common.enums.EnumTeams;
import electroblob.wizardry.constants.Element;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;

public interface INexusWarCapabilities {


    public int getLevel();
    public void setLevel(int amount);
    public EnumTeams getTeam();
    public void setTeam(EnumTeams team);
    public double getEXP();
    public void setEXP(double xp);
    public NexusClass getNexusClass();
    public void setNexusClass(NexusClass nexusClass);
    public double getMana();
    public double getMaxMana();
    public double getMPS();
    public WizardRank getWizardRank();
    public void setWizardRank(WizardRank wizardRank);
    public ArrayList<Element> getWizardElements();
    public void setWizardElement(Element element);
    public void setMana(double amount);
    public void setMaxMana(double amount);
    public void setMPS(double amount);
    public NexusInventory nexusInventory();
    public boolean isUsingNexusInventory();
    public void setIsUsingNexusInventory(boolean result);
    public void setNexusInventory(EntityPlayer player);
    public NBTTagCompound saveNBTData();
    public void Tick();

}
