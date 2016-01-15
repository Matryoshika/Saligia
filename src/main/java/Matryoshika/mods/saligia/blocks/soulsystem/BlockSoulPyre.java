package Matryoshika.mods.saligia.blocks.soulsystem;

import java.util.Random;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulBrazier;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulPyre;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSoulPyre extends BlockContainer implements ITileEntityProvider {
	
	public BlockSoulPyre (Block BlockSoulPyre){
		super(Material.rock);
		setStepSound(soundTypeStone);
		setBlockTextureName(saligia.MODID+":BlockSoulPyre");
		setHardness(10);
		this.setResistance(150);
		this.setBlockName("SoulPyre");
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
		return new TileSoulPyre();
	}
	
	public Item getItemDropped(int par1, Random rand, int par2){
        return Item.getItemFromBlock(this);
    }
}
