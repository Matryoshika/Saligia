package Matryoshika.mods.saligia.blocks;

import javax.swing.Icon;

import Matryoshika.mods.saligia.saligia;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockGhastly extends Block {
	
	@SideOnly(Side.CLIENT)
    private IIcon GhastlySides;
    @SideOnly(Side.CLIENT)
    private IIcon GhastlyFace;
	
	public BlockGhastly (Block BlockGhastly){
		super(Material.water);
		setStepSound(soundTypeStone);
		setHardness(10);
		this.setResistance(150);
		this.setBlockName("BlockGhastly");
	}
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? this.GhastlySides : (side == 0 ? this.GhastlySides : (side != meta ? this.blockIcon : this.GhastlyFace));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(saligia.MODID+":BlockGhastly");
        this.GhastlySides = iconRegister.registerIcon(saligia.MODID+":BlockGhastlySides");
    }
	
	
	public boolean isOpaqueCube()
    {
    	return false;
    }
}
