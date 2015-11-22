package Matryoshika.mods.saligia.items;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public class ItemSoulVortex extends Item {
	public ItemSoulVortex(ToolMaterial soul){
		super();
		this.maxStackSize = 16;
		this.setUnlocalizedName("ItemSoulVortex");
		this.setTextureName(saligia.MODID+":soul");
	}
	
}
