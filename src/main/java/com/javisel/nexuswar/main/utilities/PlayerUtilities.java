package com.javisel.nexuswar.main.utilities;

import com.javisel.nexuswar.common.entitycapabilities.INexusWarCapabilities;
import com.javisel.nexuswar.common.entitycapabilities.NexusWarCapabilitiesProvider;
import com.sun.org.apache.xpath.internal.operations.Bool;
import electroblob.wizardry.constants.Tier;
import electroblob.wizardry.spell.Spell;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerUtilities {


    public static boolean canWizardCastSpell(Spell s, EntityPlayer player) {
        INexusWarCapabilities nexusWarCapabilities = player.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY,null);
        boolean cancast=true;
        for (int i =0; i<nexusWarCapabilities.getWizardElements().size();i++) {
            cancast = false;
            if (nexusWarCapabilities.getWizardElements().get(i).equals(s.element)) {
                cancast=true;
                break;
            }

        }


        if (s.tier == Tier.BASIC && nexusWarCapabilities.getWizardRank().meta()<1) {
            cancast = false;
        } else if (s.tier == Tier.APPRENTICE && nexusWarCapabilities.getWizardRank().meta()<2) {
            cancast = false;
        } else if (s.tier == Tier.ADVANCED && nexusWarCapabilities.getWizardRank().meta()<3) {
            cancast = false;
        }
        else if (s.tier==Tier.MASTER && nexusWarCapabilities.getWizardRank().meta()<4){
            cancast = false;
        }

        return  cancast;

    }
}
