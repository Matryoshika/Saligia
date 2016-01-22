package Matryoshika.mods.saligia.items;

import java.text.DecimalFormat;

import org.lwjgl.opengl.GL11;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.rendering.GUI.book.GUILibroSaligia;
import Matryoshika.mods.saligia.rendering.GUIHandler.MSGuiHandler;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulBrazier;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulNexus;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulObelisk;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemLibroSaligia extends Item {
	
	int X;
	int Y;
	int Z;
	
	public ItemLibroSaligia(ToolMaterial soul){
		super();
		this.maxStackSize = 1;
		this.setUnlocalizedName("ItemLibroSaligia");
		this.setTextureName(saligia.MODID+":LibroSaligia");
	}
	
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!player.isSneaking()){
			player.openGui((Object) saligia.instance, MSGuiHandler.GUI_LIBROSALIGIA, world, (int) player.posX, (int) player.posY, (int) player.posZ);
		}
		return super.onItemRightClick(stack, world, player);
	}
	
}
