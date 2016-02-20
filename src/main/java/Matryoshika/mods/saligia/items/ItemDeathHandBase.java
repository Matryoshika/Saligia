package Matryoshika.mods.saligia.items;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public class ItemDeathHandBase extends Item {
	
	public ItemDeathHandBase(ToolMaterial sin) {
		super();
		
		this.setUnlocalizedName("ItemDeathHandBase");
		this.setTextureName(saligia.MODID+":sickleBase");
	}

	
}
