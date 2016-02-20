package Matryoshika.mods.saligia.items;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import Matryoshika.mods.saligia.saligia;

public class ItemSinDagger extends ItemSword {
	
	public ItemSinDagger(ToolMaterial sin) {
		super(sin);
		
		this.setUnlocalizedName("ItemSinDagger");
		this.setTextureName(saligia.MODID+":dagger");
	}

	
}