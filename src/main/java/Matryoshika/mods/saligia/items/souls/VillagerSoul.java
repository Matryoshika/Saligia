package Matryoshika.mods.saligia.items.souls;

import java.util.List;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.EnumChatFormatting;

public class VillagerSoul extends Item{
	public VillagerSoul(ToolMaterial soul){
		super();
		this.maxStackSize = 16;
		this.setUnlocalizedName("ItemVillagerSoul");
		this.setTextureName(saligia.MODID+":soul");
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par){
		list.add(EnumChatFormatting.DARK_GRAY + "This belonged to the soul of an innocent...");
	}
	@Override
	public boolean hasEffect(ItemStack stack){
		return true;
	}

}
