package Matryoshika.mods.saligia.items.souls;

import java.util.List;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.EnumChatFormatting;

public class Soul extends Item{
	public Soul(ToolMaterial soul){
		super();
		this.maxStackSize = 16;
		this.setUnlocalizedName("ItemSoul");
	}
	@Override
	public boolean hasEffect(ItemStack stack){
		return true;
	}

}

