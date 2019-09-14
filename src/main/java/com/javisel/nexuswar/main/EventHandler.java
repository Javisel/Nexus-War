package com.javisel.nexuswar.main;

import com.javisel.nexuswar.common.classes.framework.NexusClass;
import com.javisel.nexuswar.common.classes.warrior.ClassRune;
import com.javisel.nexuswar.common.classes.warrior.GrandstandResurrection;
import com.javisel.nexuswar.common.classes.wizard.WizardRank;
import com.javisel.nexuswar.common.enchantments.ArcanaShield;
import com.javisel.nexuswar.common.entitycapabilities.INexusWarCapabilities;
import com.javisel.nexuswar.common.entitycapabilities.NexusWarCapabilitiesProvider;
import com.javisel.nexuswar.common.enums.EnumTeams;
import com.javisel.nexuswar.common.items.ModItems;
import com.javisel.nexuswar.common.mobeffects.ModMobEffects;
import com.javisel.nexuswar.main.utilities.ItemUtilities;
import com.javisel.nexuswar.main.utilities.MobUtilities;
import com.javisel.nexuswar.main.utilities.PlayerUtilities;
import electroblob.wizardry.constants.Tier;
import electroblob.wizardry.event.SpellCastEvent;
import electroblob.wizardry.item.ItemScroll;
import electroblob.wizardry.item.ItemWand;
import electroblob.wizardry.spell.Spell;
import electroblob.wizardry.util.WandHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {

    @SubscribeEvent
    public void RockSkin(LivingHurtEvent e) {

        if (e.getSource() != DamageSource.MAGIC)
            if (e.getEntityLiving() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) e.getEntityLiving();
                if (ItemUtilities.HasItem(player, ModItems.RockSkin)) {
                    e.setAmount(e.getAmount() * 0.9F);

                }
            }

    }

    @SubscribeEvent
    public void disableItemDrop(ItemTossEvent e) {

        if (e.getEntityItem().getItem().hasTagCompound() && e.getEntityItem().getItem().getTagCompound().hasKey("cantdrop")) {
            e.setCanceled(true);
            e.getPlayer().addItemStackToInventory(e.getEntityItem().getItem());
        } else {


        }

    }


    @SubscribeEvent
    public void Ressurrection(LivingDeathEvent e) {

        if (e.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer) e.getEntityLiving();

            if (ItemUtilities.HasItem(p, ModItems.GrandStandResurrection) && !p.getCooldownTracker().hasCooldown(ModItems.GrandStandResurrection)) {

                ItemStack s = ItemUtilities.getItemstackfromitem(p, ModItems.GrandStandResurrection);
                GrandstandResurrection r = (GrandstandResurrection) s.getItem();
                r.startCooldown(p);
                p.heal(p.getMaxHealth());
                p.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 1));
                if (s.getMetadata() == 1) {
                    p.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 100, 2));
                    p.addPotionEffect(new PotionEffect(MobEffects.SPEED, 100, 2));

                }
                e.setCanceled(true);
            }

        }


    }

    @SubscribeEvent
    public void OnHitEffectHandler(LivingHurtEvent e) {

        if (e.getSource().getTrueSource() instanceof EntityPlayer) {

            EntityPlayer player = (EntityPlayer) e.getSource().getTrueSource();

            if (player.getActivePotionEffect(ModMobEffects.HeavyHit) != null) {

                e.setAmount(e.getAmount() * 1.5F);
                player.removePotionEffect(ModMobEffects.HeavyHit);
            }
            if (player.getActivePotionEffect(ModMobEffects.StunningStrike) != null) {

                e.getEntityLiving().addPotionEffect(new PotionEffect(ModMobEffects.StunningStrike, 30, 0));
                player.removePotionEffect(ModMobEffects.StunningStrike);

            }

        }

    }

    @SubscribeEvent
    public void DamageHandler(LivingAttackEvent e) {

        if (!MobUtilities.canHurt(e.getSource().getTrueSource(), e.getEntityLiving())) {
            e.setCanceled(true);
        }
        if (e.getEntityLiving().getActivePotionEffect(ModMobEffects.Invincibility) != null) {
            e.setCanceled(true);

        }
    }


    @SubscribeEvent
    public void Stunned(LivingEvent e) {

        if (e.isCancelable() && e.getEntityLiving().getActivePotionEffect(ModMobEffects.Stun) != null) {

            if (e instanceof LivingEntityUseItemEvent) {
                e.setCanceled(true);
            }
            if (e instanceof LivingAttackEvent) {

                if (((LivingAttackEvent) e).getSource().getTrueSource() instanceof EntityLivingBase) {
                    EntityLivingBase base = (EntityLivingBase) ((LivingAttackEvent) e).getSource().getTrueSource();

                    e.setCanceled(true);

                }


            }
            if (e instanceof LivingDestroyBlockEvent) {
                e.setCanceled(true);
            }

            if (e instanceof PlayerInteractEvent) {
                e.setCanceled(true);
            }


        }

    }


    @SubscribeEvent
    public void Transform(LivingHurtEvent e) {

        if (e.getSource().getTrueSource() instanceof EntityLivingBase) {
            if (MobUtilities.isNexusCombat(e.getEntityLiving(), e.getSource().getTrueSource())) {
                if (e.getSource().getTrueSource() instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) e.getSource().getTrueSource();
                    if (!e.getSource().getTrueSource().getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY, null).isUsingNexusInventory()) {
                        ItemUtilities.SwapInventories((EntityPlayer) e.getSource().getTrueSource());

                        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                            if (player.inventory.getStackInSlot(i).getItem() instanceof ClassRune) {
                                player.getCooldownTracker().setCooldown(player.inventory.getStackInSlot(i).getItem(), 1200);
                            }
                        }
                    }
                }
                if (e.getEntityLiving() instanceof EntityPlayer) {
                    EntityPlayer victimplayer = (EntityPlayer) e.getSource().getTrueSource();
                    if (!e.getEntityLiving().getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY, null).isUsingNexusInventory()) {
                        ItemUtilities.SwapInventories((EntityPlayer) e.getEntityLiving());

                        for (int i = 0; i < victimplayer.inventory.getSizeInventory(); i++) {
                            if (victimplayer.inventory.getStackInSlot(i).getItem() instanceof ClassRune) {
                                victimplayer.getCooldownTracker().setCooldown(victimplayer.inventory.getStackInSlot(i).getItem(), 1200);
                            }
                        }
                    }
                }


            }

        }
    }

    @SubscribeEvent
    public void Disarmed(LivingEntityUseItemEvent e) {

        if (e.getEntityLiving().getActivePotionEffect(ModMobEffects.Disarm) != null) {
            e.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void DisarmedAttack(AttackEntityEvent e) {
        if (e.getEntityLiving().getActivePotionEffect(ModMobEffects.Disarm) != null) {
            e.setCanceled(true);
        }


    }

    @SubscribeEvent
    public void SpellManaFix(SpellCastEvent e) {

        if (e.getSource() == SpellCastEvent.Source.WAND) {

            if (e.getEntityLiving() instanceof EntityPlayer) {
                EntityPlayer wizard = (EntityPlayer) e.getEntityLiving();

                if (e instanceof SpellCastEvent.Pre) {
                    INexusWarCapabilities nexusWarCapabilities = wizard.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY, null);


                    e.setCanceled(PlayerUtilities.canWizardCastSpell(e.getSpell(), wizard));


                    if (nexusWarCapabilities.getMana() < e.getSpell().cost) {
                        e.setCanceled(true);
                    } else {
                        nexusWarCapabilities.setMana(nexusWarCapabilities.getMana() - e.getSpell().cost);
                        //TODO ADD NETWORKING CALL
                    }


                }
                if (e instanceof SpellCastEvent.Post) {

                    for (int i = 0; i < wizard.inventory.getSizeInventory(); i++) {
                        if (wizard.inventory.getStackInSlot(i).getItem() instanceof ItemWand) {
                            wizard.inventory.getStackInSlot(i).setItemDamage(0);
                        }
                    }

                }
            }

        }

    }

    @SubscribeEvent
    public void Execute(PotionEvent.PotionRemoveEvent e) {
        if (e.getPotionEffect().getPotion() == ModMobEffects.Execution) {

            e.getEntityLiving().attackEntityFrom(DamageSource.MAGIC, e.getEntityLiving().getMaxHealth() * 1000000000);
        }
    }

    @SubscribeEvent
    public void SpellGating(PlayerInteractEvent.RightClickItem e) {

        boolean cancast = true;
        INexusWarCapabilities nexusWarCapabilities = e.getEntityPlayer().getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY, null);


        if (e.getItemStack().getItem() instanceof ItemWand) {
            e.getItemStack().setItemDamage(0);
            if (nexusWarCapabilities.getNexusClass() != NexusClass.WIZARD) {
                cancast = false;
            } else {

            }


        } else {


            if (e.getItemStack().getItem() instanceof ItemScroll) {
                if (nexusWarCapabilities.getNexusClass() != NexusClass.WIZARD) {
                    if (e.getItemStack().hasTagCompound()) {

                        for (int i = 0; i < e.getItemStack().getEnchantmentTagList().tagCount(); i++) {

                            short id = e.getItemStack().getEnchantmentTagList().getCompoundTagAt(i).getShort("id");
                            short level = e.getItemStack().getEnchantmentTagList().getCompoundTagAt(i).getShort("lvl");
                            if (Enchantment.getEnchantmentByID(id) instanceof ArcanaShield) {

                                if (Spell.get(e.getItemStack().getItemDamage()).tier == Tier.BASIC && level < 1) {
                                    cancast = false;
                                }
                                if (Spell.get(e.getItemStack().getItemDamage()).tier == Tier.APPRENTICE && level < 2) {
                                    cancast = false;

                                }
                                if (Spell.get(e.getItemStack().getItemDamage()).tier == Tier.ADVANCED && level < 3) {
                                    cancast = false;

                                }
                                if (Spell.get(e.getItemStack().getItemDamage()).tier == Tier.MASTER && level < 4) {
                                    cancast = false;

                                }

                            }

                        }


                    }


                }

            }

        }

        if (!cancast) {
            e.setCanceled(true);
        }
    }

}
