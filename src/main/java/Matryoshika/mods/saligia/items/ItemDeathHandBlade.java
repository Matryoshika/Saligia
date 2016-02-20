package Matryoshika.mods.saligia.items;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public class ItemDeathHandBlade extends Item {
	
	public ItemDeathHandBlade(ToolMaterial sin) {
		super();
		
		this.setUnlocalizedName("ItemDeathHandBlade");
		this.setTextureName(saligia.MODID+":sickleBlade");
	}

	
}
