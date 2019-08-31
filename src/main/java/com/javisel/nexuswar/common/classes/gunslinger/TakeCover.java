package com.javisel.nexuswar.common.classes.gunslinger;

import com.javisel.nexuswar.common.classes.AbilityItem;
import com.javisel.nexuswar.main.utilities.BlockUtilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class TakeCover extends AbilityItem {

    public TakeCover() {
    super("gunslinger_ability_takecover", 18000);
    // TODO Auto-generated constructor stub
}


    @Override

    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

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

        startCooldown(playerIn);
        int i = MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 0.3D) & 3;


        BlockPos place = new BlockPos(0,0,0);

        BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

        for (int x1 = -3; x1 < 3; x1++) {
            place = new BlockPos(playerIn.posX + x1, playerIn.posY + 0.3D, playerIn.posZ + 3);
            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos(playerIn.posX + x1 * (-1), playerIn.posY + 0.3D, playerIn.posZ + 3);
            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos( playerIn.posX + 3, playerIn.posY + 0.3D, playerIn.posZ + x1);

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos(playerIn.posX + 3, playerIn.posY + 0.3D, playerIn.posZ + x1 * (-1));

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos(playerIn.posX + x1, playerIn.posY + 0.3D, playerIn.posZ - 3);

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos( playerIn.posX + x1 * (-1), playerIn.posY + 0.3D, playerIn.posZ - 3);

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos( playerIn.posX - 3, playerIn.posY + 0.3D, playerIn.posZ + x1);

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);
            place = new BlockPos(playerIn.posX - 3, playerIn.posY + 0.3D, playerIn.posZ + x1 * (-1));

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);


        }

        for (int x1 = -3; x1 < 3; x1++) {
            place = new BlockPos(playerIn.posX + x1, playerIn.posY + 1 + 0.3D, playerIn.posZ + 3);
            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos(playerIn.posX + x1 * (-1), playerIn.posY + 1 + 0.3D, playerIn.posZ + 3);
            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos( playerIn.posX + 3, playerIn.posY + 1 + 0.3D, playerIn.posZ + x1);

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos(playerIn.posX + 3, playerIn.posY + 1 + 0.3D, playerIn.posZ + x1 * (-1));

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos(playerIn.posX + x1, playerIn.posY + 1 + 0.3D, playerIn.posZ - 3);

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos( playerIn.posX + x1 * (-1), playerIn.posY + 1 + 0.3D, playerIn.posZ - 3);

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos( playerIn.posX - 3, playerIn.posY + 1 + 0.3D, playerIn.posZ + x1);

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);
            place = new BlockPos(playerIn.posX - 3, playerIn.posY + 1 + 0.3D, playerIn.posZ + x1 * (-1));

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);


        }

        for (int x1 = -2; x1 < 2; x1++) {
            place = new BlockPos(playerIn.posX + x1, playerIn.posY + 0.2D, playerIn.posZ + 2);
            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos(playerIn.posX + x1 * (-1), playerIn.posY + 0.2D, playerIn.posZ + 2);
            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos( playerIn.posX + 2, playerIn.posY + 0.2D, playerIn.posZ + x1);

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos(playerIn.posX + 2, playerIn.posY + 0.2D, playerIn.posZ + x1 * (-1));

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos(playerIn.posX + x1, playerIn.posY + 0.2D, playerIn.posZ - 2);

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos( playerIn.posX + x1 * (-1), playerIn.posY + 0.2D, playerIn.posZ - 2);

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos( playerIn.posX - 2, playerIn.posY + 0.2D, playerIn.posZ + x1);

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);
            place = new BlockPos(playerIn.posX - 2, playerIn.posY + 0.2D, playerIn.posZ + x1 * (-1));

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);


        }


        for (int x1 = -1; x1 < 1; x1++) {
            place = new BlockPos(playerIn.posX + x1, playerIn.posY-1 + 0.1D, playerIn.posZ + 1);
            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos(playerIn.posX + x1 * (-1), playerIn.posY-1 + 0.1D, playerIn.posZ + 1);
            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos( playerIn.posX + 1, playerIn.posY-1 + 0.1D, playerIn.posZ + x1);

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos(playerIn.posX + 1, playerIn.posY-1 + 0.1D, playerIn.posZ + x1 * (-1));

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos(playerIn.posX + x1, playerIn.posY-1 + 0.1D, playerIn.posZ - 1);

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos( playerIn.posX + x1 * (-1), playerIn.posY-1 + 0.1D, playerIn.posZ - 1);

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);

            place = new BlockPos( playerIn.posX - 1, playerIn.posY-1 + 0.1D, playerIn.posZ + x1);

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);
            place = new BlockPos(playerIn.posX - 1, playerIn.posY-1 + 0.1D, playerIn.posZ + x1 * (-1));

            BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);


        }
        place = new BlockPos(playerIn.posX,playerIn.posY-1,playerIn.posZ);
        BlockUtilities.setBlock(worldIn, place, Blocks.STONE, true);


        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        EntityPlayer p = Minecraft.getMinecraft().player;
        tooltip.add("Class: Gunslinger");
        tooltip.add("ACTIVE");
        tooltip.add("Type: Construct");
        tooltip.add("Range: 6x3x6");
        tooltip.add(
                " Create a large constructed flipped-pyramid to use as a shield and sniper nest.");


    }
}
