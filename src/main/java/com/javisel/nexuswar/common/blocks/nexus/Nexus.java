package com.javisel.nexuswar.common.blocks.nexus;



import com.javisel.nexuswar.common.blocks.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Nexus extends BlockBase {

    public Nexus( String name) {
        super(Material.GLASS, name);
        this.setHardness(12);
        this.setResistance(50);
        this.setLightLevel(1.0F);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {

        return false;
    }



    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }


}
