/**
 * 
 */
package se.Matryoshika.Saligia.Content.Items;

import net.minecraft.item.Item;
import se.Matryoshika.Saligia.Saligia;

/**
 * This class was created by Matryoshika Aug 16, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class ItemDustCompound extends Item{
	
	public ItemDustCompound(){
		this.setCreativeTab(Saligia.saligiaTab);
		this.setRegistryName("ItemDustCompound");
		this.setUnlocalizedName(getRegistryName().toString());
	}

}
