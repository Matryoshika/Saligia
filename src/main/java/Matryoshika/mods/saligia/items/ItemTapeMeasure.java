package Matryoshika.mods.saligia.items;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemTapeMeasure extends Item {
	
	int xa;
	int ya;
	int za;
	int xb;
	int yb;
	int zb;
	int X;
	int Y;
	int Z;
	ChatComponentTranslation chatComponent;
	
	
	public ItemTapeMeasure(ToolMaterial soul){
		super();
		this.maxStackSize = 16;
		this.setUnlocalizedName("ItemTapeMeasure");
		this.setTextureName(saligia.MODID+":");
	}
	
	public ItemStack onItemRightClick(ItemStack tape, World world, EntityPlayer player){
		if(player.isSneaking() && Minecraft.getMinecraft().objectMouseOver!=null && world.isRemote == false){
			xa =+ Minecraft.getMinecraft().objectMouseOver.blockX;
			ya =+ Minecraft.getMinecraft().objectMouseOver.blockY;
			za =+ Minecraft.getMinecraft().objectMouseOver.blockZ;
		}
		if(!player.isSneaking() && Minecraft.getMinecraft().objectMouseOver!=null && world.isRemote == false){
			xb =+ Minecraft.getMinecraft().objectMouseOver.blockX;
			yb =+ Minecraft.getMinecraft().objectMouseOver.blockY;
			zb =+ Minecraft.getMinecraft().objectMouseOver.blockZ;
			CountAxis(player);
			
			if(world.getTileEntity(xb, yb, zb) != null){
				System.out.println("Has a tileentity");
			}
		}
		
		return tape;	
	}
	
	public void CountAxis(EntityPlayer player){
		X = xb-xa;
		Y = yb-ya;
		Z = zb-za;
		chatComponent = (ChatComponentTranslation) new ChatComponentTranslation("{"+X+","+Y+","+Z+"}").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.WHITE));
		player.addChatMessage(chatComponent);
	}
	
}