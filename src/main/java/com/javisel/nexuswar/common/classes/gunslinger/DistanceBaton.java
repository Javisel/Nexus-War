package com.javisel.nexuswar.common.classes.gunslinger;

import com.javisel.nexuswar.common.classes.AbilityItem;
import electroblob.wizardry.registry.WizardrySounds;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class DistanceBaton extends AbilityItem {
    public DistanceBaton() {
        super("gunslinger_ability_distancebaton", 1200,0);
        this.hasSubtypes=false;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        super.initializeItem(stack);
        if  (stack.getEnchantmentTagList().isEmpty()) {
            stack.addEnchantment(Enchantments.KNOCKBACK, 5);
        }

    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {

        if (attacker instanceof EntityPlayer) {
            EntityPlayer swinger = (EntityPlayer) attacker;


                this.startCooldown(swinger);

        }



        return super.hitEntity(stack, target, attacker);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {

        if  (player.getCooledAttackStrength(0)!=1.0F || player.getCooldownTracker().hasCooldown(this)) {
            return true;
        }
            return false;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Class: Gunslinger");
        tooltip.add("Type: Item");
        tooltip.add("Effects: Enemies");
        tooltip.add("On hitting a target, knock them back a far distance. 60s cooldown.");


        }

    }
