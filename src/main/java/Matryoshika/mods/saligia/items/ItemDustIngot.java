package Matryoshika.mods.saligia.items;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public class ItemDustIngot extends Item {
	
	public ItemDustIngot(ToolMaterial sin) {
		super();
		
		this.setUnlocalizedName("ItemDustIngot");
		this.setTextureName(saligia.MODID+":sinDustIngot");
	}

	
}
