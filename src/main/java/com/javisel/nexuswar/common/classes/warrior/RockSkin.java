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

public class RockSkin extends AbilityItem {
    public RockSkin() {
        super("warrior_ability_rockskin",0);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

        tooltip.add("Class: Warrior");
        tooltip.add("PASSIVE");
        tooltip.add("Type: Buff");
        tooltip.add("Effects: Self");

        tooltip.add(
                "You take 10% less damage from all non-magical sources.");

    }
}
