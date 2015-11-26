package Matryoshika.mods.saligia.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class ItemBlockGhastly extends ItemBlock{
	
	public String tooltip1;
	public String tooltip2;
	
	public ItemBlockGhastly(Block block){
		super(block);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool){
		tooltip1 = new String(StatCollector.translateToLocal("saligia.blockghastly.tooltip1"));
		tooltip2 = new String(StatCollector.translateToLocal("saligia.blockghastly.tooltip2"));
		list.add(EnumChatFormatting.GRAY + tooltip1);
		list.add(EnumChatFormatting.GRAY + tooltip2);
	}

}
