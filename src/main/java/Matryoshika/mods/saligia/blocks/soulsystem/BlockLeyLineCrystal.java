package Matryoshika.mods.saligia.blocks.soulsystem;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.tile.soulsystem.TileLeyLineCrystal;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockLeyLineCrystal extends BlockContainer implements ITileEntityProvider{
	
	public BlockLeyLineCrystal (Block BlockLeyLineCrystal){
		super(Material.glass);
		setStepSound(soundTypeGlass);
		setBlockTextureName(saligia.MODID+":BlockLeyLineCrystal");
		setHardness(10);
		this.setResistance(150);
		this.setBlockName("LeyLineCrystal");
		this.setBlockBounds(0.40F, 0.40F, 0.40F, 0.60F, 0.60F, 0.60F);
	}
	
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	 @SideOnly(Side.CLIENT)
	 public int getRenderBlockPass()
	 {
	     return 1;
	 }
	 
	@Override
	public boolean isOpaqueCube(){
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileLeyLineCrystal();
	}

}
