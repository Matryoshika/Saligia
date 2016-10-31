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
public class ItemCreeperAshJar extends Item{
	
	public ItemCreeperAshJar(){
		this.setRegistryName(Saligia.MODID, "creeperashjar");
		this.setUnlocalizedName(getRegistryName().toString());
		this.setCreativeTab(Saligia.saligiaTab);
	}

}
