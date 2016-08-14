package se.Matryoshika.Saligia.Content.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import se.Matryoshika.Saligia.Saligia;

/**
 * This class was created by Matryoshika Aug 9, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class BlockCharcoal extends Block{
	
	public BlockCharcoal(){
		super(Material.GROUND);
		this.setHardness(1);
		this.setResistance(0);
		this.setRegistryName(Saligia.MODID, "BlockCharcoal");
		this.setUnlocalizedName(this.getRegistryName().toString());
		this.setCreativeTab(Saligia.saligiaTab);
	}

}
