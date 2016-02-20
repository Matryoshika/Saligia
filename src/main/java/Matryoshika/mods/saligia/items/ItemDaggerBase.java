package Matryoshika.mods.saligia.items;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public class ItemDaggerBase extends Item {
	
	public ItemDaggerBase(ToolMaterial sin) {
		super();
		
		this.setUnlocalizedName("ItemDaggerBase");
		this.setTextureName(saligia.MODID+":daggerBody");
	}

	
}
