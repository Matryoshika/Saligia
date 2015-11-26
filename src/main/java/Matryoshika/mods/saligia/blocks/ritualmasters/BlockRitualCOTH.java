package Matryoshika.mods.saligia.blocks.ritualmasters;

import java.util.List;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.blocks.BlockRitualCentre;
import Matryoshika.mods.saligia.tile.TileRitualCOTH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockRitualCOTH extends BlockContainer implements ITileEntityProvider {
	
	@SideOnly(Side.CLIENT)
    private IIcon BlockRitualCentreTop;
    @SideOnly(Side.CLIENT)
    private IIcon BlockRitualCentreSides;
	
	public BlockRitualCOTH (Block BlockRitualCentre){
		super(Material.rock);
		setStepSound(soundTypeStone);
		setBlockTextureName(saligia.MODID+":BlockRitualCentre");
		setHardness(10);
		this.setResistance(150);
		this.setBlockName("BlockRitualCOTH");
		this.isBlockContainer = true;
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta){
        return side == 1 ? this.BlockRitualCentreTop : (side == 0 ? this.blockIcon : (side != meta ? this.blockIcon : this.BlockRitualCentreTop));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
        this.BlockRitualCentreTop = iconRegister.registerIcon(saligia.MODID+":BlockRitualCentreTop");
        this.blockIcon = iconRegister.registerIcon(saligia.MODID+":BlockRitualCentreSides");
    }
	
	@Override
    public boolean hasTileEntity(){
        return true;
    }
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z){
        world.setTileEntity(x, y, z, this.createTileEntity(world, world.getBlockMetadata(x, y, z)));
    }
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta){
	        super.breakBlock(world, x, y, z, block, meta);
	        world.removeTileEntity(x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileRitualCOTH();
	}

}
