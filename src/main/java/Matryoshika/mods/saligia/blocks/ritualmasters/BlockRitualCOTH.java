package Matryoshika.mods.saligia.blocks.ritualmasters;

import java.util.List;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.blocks.BlockRitualCentre;
import Matryoshika.mods.saligia.tile.TileRitualCOTH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockRitualCOTH extends BlockRitualCentre {
	
	public BlockRitualCOTH (Block BlockRitualCentre){
		super(BlockRitualCentre);
		setStepSound(soundTypeStone);
		setBlockTextureName(saligia.MODID+":BlockRitualCentre");
		setHardness(10);
		this.setResistance(150);
		this.setBlockName("BlockRitualCOTH");
	}
	
	@Override
	public TileEntity createTileEntity(World world, int meta){
	return new TileRitualCOTH();
	}
	
	@Override
    public boolean hasTileEntity(){
        return true;
    }
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta){
	        super.breakBlock(world, x, y, z, block, meta);
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z){
        world.setTileEntity(x, y, z, this.createTileEntity(world, world.getBlockMetadata(x, y, z)));
    }
}
