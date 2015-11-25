package Matryoshika.mods.saligia.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemBlockGhastly extends ItemBlock{
	
	public ItemBlockGhastly(Block block){
		super(block);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool){
		list.add(EnumChatFormatting.GRAY + "As ethereal as smoke, this block ");
		list.add(EnumChatFormatting.GRAY + "may vanish at the slightest touch");
	}

}
