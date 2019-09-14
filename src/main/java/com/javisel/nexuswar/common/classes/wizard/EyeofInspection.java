package com.javisel.nexuswar.common.classes.wizard;

import com.javisel.nexuswar.common.classes.AbilityItem;
import electroblob.wizardry.WizardData;
import electroblob.wizardry.event.DiscoverSpellEvent;
import electroblob.wizardry.item.ItemIdentificationScroll;
import electroblob.wizardry.item.ItemScroll;
import electroblob.wizardry.item.ItemSpellBook;
import electroblob.wizardry.registry.WizardryAdvancementTriggers;
import electroblob.wizardry.spell.Spell;
import electroblob.wizardry.util.WizardryUtilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import java.util.Iterator;

public class EyeofInspection extends AbilityItem {
    public EyeofInspection() {
        super("wizard_ability_eyeofinspection", 20*60*60*2, 0);
        this.setMaxStackSize(3);
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        int durabilitydisplay = 0;

        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("chargetime")) {
            durabilitydisplay = stack.getTagCompound().getInteger("chargetime") / 20*60*60*2;
        }

        return durabilitydisplay ;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityIn;
            if (!stack.getTagCompound().hasKey("charges")) {
                stack.getTagCompound().setInteger("charges", 1);
                stack.getTagCompound().setInteger("chargetime",0);
            }
            if (!isOnCooldown(player) && stack.getCount() < stack.getTagCompound().getInteger("charges")) {
                stack.getTagCompound().setInteger("chargetime",stack.getTagCompound().getInteger("chargetime")-1);
                if (stack.getCount() < stack.getTagCompound().getInteger("charges") && stack.getTagCompound().getInteger("chargetime") ==0) {
                    stack.getTagCompound().setInteger("chargetime",20*60*60*2);
                }
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (WizardData.get(player) != null) {
            WizardData properties = WizardData.get(player);
            Iterator var6 = WizardryUtilities.getPrioritisedHotbarAndOffhand(player).iterator();

            ItemStack stack1;
            Spell spell;
            do {
                do {
                    do {
                        if (!var6.hasNext()) {
                            if (!world.isRemote) {
                                player.sendMessage(new TextComponentTranslation("item.ebwizardry:identification_scroll.nothing_to_identify", new Object[0]));
                            }

                            return new ActionResult(EnumActionResult.FAIL, stack);
                        }

                        stack1 = (ItemStack)var6.next();
                    } while(stack1.isEmpty());

                    spell = Spell.get(stack1.getItemDamage());
                } while(!(stack1.getItem() instanceof ItemSpellBook) && !(stack1.getItem() instanceof ItemScroll));
            } while(properties.hasSpellBeenDiscovered(spell) || MinecraftForge.EVENT_BUS.post(new DiscoverSpellEvent(player, spell, DiscoverSpellEvent.Source.IDENTIFICATION_SCROLL)));

            properties.discoverSpell(spell);
            WizardryAdvancementTriggers.identify_spell.triggerFor(player);
            player.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 1.25F, 1.0F);
            if (!player.capabilities.isCreativeMode) {


                if (stack.getCount()==1){
                    this.startCooldown(player);
                }
             else   if (stack.getCount()>1) {
                    stack.shrink(1);
                }


            }

            if (!world.isRemote) {
                player.sendMessage(new TextComponentTranslation("spell.discover", new Object[]{spell.getNameForTranslationFormatted()}));
            }

            return new ActionResult(EnumActionResult.SUCCESS, stack);
        } else {
            return new ActionResult(EnumActionResult.FAIL, stack);
        }
    }
}
