package com.javisel.nexuswar.common.classes.warrior;

import com.javisel.nexuswar.common.classes.AbilityItem;
import com.javisel.nexuswar.main.utilities.BlockUtilities;
import com.javisel.nexuswar.main.utilities.MobUtilities;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class BattleStomp extends AbilityItem {
    public BattleStomp() {
        super("warrior_ability_battlestomp",150,0);
    }
    @Override

    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

       super.initializeItem(stack);
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer) entityIn;
            if (isSelected && !p.getCooldownTracker().hasCooldown(this)) {
                if (worldIn.isRemote) {
                    for (int x = -6; x < 6; x++) {

                        worldIn.spawnParticle(EnumParticleTypes.CRIT, p.posX + x, p.posY + 0.6D, p.posZ + 6, 0.0D, 0.0D,
                                0.0D);

                        worldIn.spawnParticle(EnumParticleTypes.CRIT, p.posX + x * (-1), p.posY + 0.6D, p.posZ + 6, 0.0D,
                                0.0D, 0.0D);

                        worldIn.spawnParticle(EnumParticleTypes.CRIT, p.posX + 6, p.posY + 0.6D, p.posZ + x, 0.0D, 0.0D,
                                0.0D);

                        worldIn.spawnParticle(EnumParticleTypes.CRIT, p.posX + 6, p.posY + 0.6D, p.posZ + x * (-1), 0.0D,
                                0.0D, 0.0D);

                        worldIn.spawnParticle(EnumParticleTypes.CRIT, p.posX + x, p.posY + 0.6D, p.posZ - 6, 0.0D, 0.0D,
                                0.0D);

                        worldIn.spawnParticle(EnumParticleTypes.CRIT, p.posX + x * (-1), p.posY + 0.6D, p.posZ - 6, 0.0D,
                                0.0D, 0.0D);

                        worldIn.spawnParticle(EnumParticleTypes.CRIT, p.posX - 6, p.posY + 0.6D, p.posZ + x, 0.0D, 0.0D,
                                0.0D);

                        worldIn.spawnParticle(EnumParticleTypes.CRIT, p.posX - 6, p.posY + 0.6D, p.posZ + x * (-1), 0.0D,
                                0.0D, 0.0D);

                    }
                }

            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

       this.startCooldown(playerIn);

        playerIn.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 300, 1));
        List<Entity> e = worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, new AxisAlignedBB(playerIn.posX + 5,
                playerIn.posY + 1, playerIn.posZ + 5, playerIn.posX - 5, playerIn.posY - 1, playerIn.posZ - 5));

        for (int i = 0; i < e.size(); i++) {

            if (e.get(i) instanceof EntityLivingBase) {
                EntityLivingBase target = (EntityLivingBase) e.get(i);


                if (!MobUtilities.canHurt(playerIn, target)) {
                    System.out.println(target.getName() + " is an ally");
                    target.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 300, 1));

                } else {
                    System.out.println(target.getName() + " is an Enemy");

                    target.attackEntityFrom(DamageSource.causeIndirectDamage(playerIn, target), 8.0F);
                    target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 2));

                    BlockUtilities.setBlock(worldIn, new BlockPos(target.posX, target.posY - 1, target.posZ),
                            Blocks.AIR, false);

                }


            }
        }

        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));

    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

        tooltip.add("Class: Warrior");
        tooltip.add("ACTIVE");
        tooltip.add("Type: Area");
        tooltip.add("Effects: All.");
        tooltip.add("Range: 5x1x5.");

        tooltip.add("Activate to stomp the earth, dealing 8 damage to enemies in range, applying slowness and destroying the earth beneath them. Grants Strength to allies.");
    }
}
