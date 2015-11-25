package Matryoshika.mods.saligia.items;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public class ItemLibroSaligia extends Item {
	public ItemLibroSaligia(ToolMaterial soul){
		super();
		this.maxStackSize = 16;
		this.setUnlocalizedName("ItemLibroSaligia");
		this.setTextureName(saligia.MODID+":LibroSaligia");
	}
	
}
