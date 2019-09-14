package com.javisel.nexuswar.common.classes.wizard;

import com.javisel.nexuswar.common.classes.framework.NexusClass;
import com.javisel.nexuswar.common.classes.warrior.ClassRune;
import com.javisel.nexuswar.common.classes.warrior.WarriorClassRune;
import com.javisel.nexuswar.common.entitycapabilities.INexusWarCapabilities;
import com.javisel.nexuswar.common.entitycapabilities.NexusWarCapabilitiesProvider;
import com.javisel.nexuswar.common.items.ModItems;
import com.javisel.nexuswar.main.utilities.ItemUtilities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class WizardClassRune extends ClassRune {
    public WizardClassRune() {
        super("wizard_classrune");
    }



    @Override
    public void suiteMeUp(EntityPlayer player) {
        player.inventory.clear();

        INexusWarCapabilities nexusWarCapabilities = player.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY,null);
        nexusWarCapabilities.setNexusClass(NexusClass.WIZARD);
        nexusWarCapabilities.setNexusInventory(player);
        nexusWarCapabilities.setWizardRank(WizardRank.NOVICE);
        nexusWarCapabilities.setMaxMana(50);
        nexusWarCapabilities.setMana(50);
        nexusWarCapabilities.setMPS(0.05);

        ItemUtilities.SwapInventories(player);

    }
}
