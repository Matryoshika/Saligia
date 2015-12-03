package Matryoshika.mods.saligia.items;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.rendering.GUI.GUILibroSaligia;
import Matryoshika.mods.saligia.rendering.GUIHandler.MSGuiHandler;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemLibroSaligia extends Item {
	public ItemLibroSaligia(ToolMaterial soul){
		super();
		this.maxStackSize = 1;
		this.setUnlocalizedName("ItemLibroSaligia");
		this.setTextureName(saligia.MODID+":LibroSaligia");
	}
	
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.openGui((Object) saligia.instance, MSGuiHandler.GUI_LIBROSALIGIA, world, (int) player.posX, (int) player.posY, (int) player.posZ);
		return super.onItemRightClick(stack, world, player);
	}
	
}
