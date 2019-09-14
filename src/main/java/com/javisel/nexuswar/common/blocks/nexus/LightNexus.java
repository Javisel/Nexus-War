package com.javisel.nexuswar.common.blocks.nexus;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.List;

public class LightNexus extends Nexus {
    public LightNexus() {
        super("light_nexus");
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {

        worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_WITHER_DEATH,
                SoundCategory.NEUTRAL, 1, 1);
        if (!worldIn.isRemote) {
            EntityLightningBolt e = new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), true);
            worldIn.addWeatherEffect(e);
            List<EntityPlayerMP> P = worldIn.getMinecraftServer().getPlayerList().getPlayers();
            for (int i = 0; i < P.size(); i++) {
                P.get(i).sendMessage(new TextComponentString("The Light Nexus has been Destroyed!"));

            }

            worldIn.setWorldTime(13000);
        }

        else {

        }
    }
}
