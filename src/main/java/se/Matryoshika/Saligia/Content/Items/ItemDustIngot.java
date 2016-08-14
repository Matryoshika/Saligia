package se.Matryoshika.Saligia.Content.Items;

import net.minecraft.item.Item;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.Content.ContentRegistry;

/**
 * This class was created by Matryoshika Aug 9, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class ItemDustIngot extends Item{
	
	public ItemDustIngot(){
		this.setCreativeTab(Saligia.saligiaTab);
		this.setRegistryName(Saligia.MODID, "ItemDustIngot");
		this.setUnlocalizedName(this.getRegistryName().toString());
	}

}
