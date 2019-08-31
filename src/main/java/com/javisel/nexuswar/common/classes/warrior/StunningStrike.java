package com.javisel.nexuswar.common.classes.warrior;

import com.javisel.nexuswar.common.classes.AbilityItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class StunningStrike extends AbilityItem {
    public StunningStrike() {
        super("warrior_ability_stunningstrike",20*5*10);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

        tooltip.add("Class: Warrior");
        tooltip.add("ACTIVE");
        tooltip.add("Type: Buff");
        tooltip.add("Effects: Self");

        tooltip.add("Your next Melee attack Stuns on hit for 1.5s");


    }
}
