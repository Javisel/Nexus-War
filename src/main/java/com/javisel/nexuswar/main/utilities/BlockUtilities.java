package com.javisel.nexuswar.main.utilities;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUtilities {
    static Block Banlist[] = { Blocks.OBSIDIAN, Blocks.BEDROCK, Blocks.COMMAND_BLOCK, Blocks.WATER };
    static Block banlistliquid[] = { Blocks.WATER, Blocks.FLOWING_WATER, Blocks.LAVA, Blocks.FLOWING_LAVA };

    public static void setBlock(World world, BlockPos BlockPos, Block block, boolean breakliquid) {

        boolean canbreak = true;

        for (int i = 0; i < Banlist.length; i++) {
            if (world.getBlockState(BlockPos).getBlock() == Banlist[i]) {
                canbreak = false;

            }
        }
        if (!breakliquid) {
            for (int i = 0; i < banlistliquid.length; i++) {
                if (world.getBlockState(BlockPos).getBlock() == banlistliquid[i]) {
                    canbreak = false;

                }
            }
        }
        if (canbreak) {
            world.setBlockState(BlockPos, block.getDefaultState());
        }

    }
}
