/**
 * 
 */
package se.Matryoshika.Saligia.Content.Blocks.Soulsystem;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.API.Soulsystem.IAnimunStorage;

/**
 * This class was created by Matryoshika Aug 15, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public abstract class AnimunContainer extends Block implements IAnimunStorage{

	protected int currentAnimun = 0;
	
	public AnimunContainer() {
		super(Material.IRON);
		this.setRegistryName("AnimunStorageTier"+storageTier());
		this.setUnlocalizedName(getRegistryName().toString());
		this.setCreativeTab(Saligia.saligiaTab);
	}
	
	

}
