/**
 * 
 */
package se.Matryoshika.Saligia.Content.Items;

import net.minecraft.item.Item;
import se.Matryoshika.Saligia.Saligia;

/**
 * This class was created by Matryoshika Oct 29, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class ItemPoisonClay extends Item{
	
	public ItemPoisonClay(){
		this.setRegistryName(Saligia.MODID, "poisonclay");
		this.setUnlocalizedName(getRegistryName().toString());
		this.setCreativeTab(Saligia.saligiaTab);
	}

}
