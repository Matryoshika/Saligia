/**
 * 
 */
package se.Matryoshika.Saligia.Content.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import se.Matryoshika.Saligia.Saligia;

/**
 * This class was created by Matryoshika Aug 12, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class BlockGhast extends Block{
	
	public BlockGhast(){
		super(Material.WATER);
		this.setSoundType(blockSoundType.CLOTH);
		this.setHardness(1);
		this.setResistance(75);
		this.setRegistryName("BlockGhast");
		this.setUnlocalizedName(this.getRegistryName().toString());
		this.setCreativeTab(Saligia.saligiaTab);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state){
		return false;
	}
}
