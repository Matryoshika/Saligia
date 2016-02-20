package Matryoshika.mods.saligia.items;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.item.Item;

public class ItemDustCompound extends Item {
	
	public ItemDustCompound(ToolMaterial sin) {
		super();
		
		this.setUnlocalizedName("ItemDustCompound");
		this.setTextureName(saligia.MODID+":dustCompound");
	}

	
}
