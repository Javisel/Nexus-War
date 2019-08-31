package com.javisel.nexuswar.common.classes.warrior;

import com.javisel.nexuswar.common.classes.AbilityItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class GrandstandResurrection extends AbilityItem {
    public GrandstandResurrection() {
        super("warrior_ability_grandstandresurrection", 60*60*24*20);
        this.hasSubtypes=true;
    }









    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        int i = stack.getMetadata();
        boolean isSelecting = false;

        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("isselecting")) {
            isSelecting = stack.getTagCompound().getBoolean("isselecting");
        }
        tooltip.add("Class: Warrior");
        tooltip.add("PASSIVE");
        tooltip.add("Type: Buff");
        tooltip.add("Effects: Self.");


        tooltip.add(" When you take fatal damage, you are revived with full health and are granted regeneration II for 5s. 24 hour cooldown.");
    }

    @Override
    public String getTranslationKey(ItemStack stack){

        int i = stack.getMetadata();
        if (i ==1) {
            return super.getTranslationKey() + ".upgraded";

        }

        return super.getTranslationKey();
    }


}
