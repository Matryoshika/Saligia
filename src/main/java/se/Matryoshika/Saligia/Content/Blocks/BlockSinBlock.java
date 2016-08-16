/**
 * 
 */
package se.Matryoshika.Saligia.Content.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.Content.ContentRegistry;

/**
 * This class was created by Matryoshika Aug 16, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class BlockSinBlock extends Block{
	
	public BlockSinBlock(){
		super(Material.IRON);
		this.setCreativeTab(Saligia.saligiaTab);
		this.setRegistryName("BlockSinBlock");
		this.setUnlocalizedName(getRegistryName().toString());
		this.setResistance(666^2);
	}

}
