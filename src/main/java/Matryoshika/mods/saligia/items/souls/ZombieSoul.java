package Matryoshika.mods.saligia.items.souls;

import java.util.List;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.EnumChatFormatting;

public class ZombieSoul extends Soul{
	public ZombieSoul(ToolMaterial soul){
		super(soul);
		this.setUnlocalizedName("ItemZombieSoul");
		this.setTextureName(saligia.MODID+":soul");
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par){
		list.add(EnumChatFormatting.DARK_GRAY + "This soul has started to become corrupt...");
	}

}