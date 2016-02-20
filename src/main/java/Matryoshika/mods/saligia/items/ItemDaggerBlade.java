package Matryoshika.mods.saligia.items;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public class ItemDaggerBlade extends Item {
	
	public ItemDaggerBlade(ToolMaterial sin) {
		super();
		
		this.setUnlocalizedName("ItemDaggerBlade");
		this.setTextureName(saligia.MODID+":daggerBlade");
	}

	
}