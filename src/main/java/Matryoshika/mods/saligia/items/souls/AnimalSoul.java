package Matryoshika.mods.saligia.items.souls;

import java.util.List;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.EnumChatFormatting;

public class AnimalSoul extends Soul{
	public AnimalSoul(ToolMaterial soul){
		super(soul);
		this.maxStackSize = 16;
		this.setUnlocalizedName("ItemAnimalSoul");
		this.setTextureName(saligia.MODID+":soul");
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par){
		list.add(EnumChatFormatting.DARK_GRAY + "Belonged to an innocent but undeveloped soul...");
	}

}