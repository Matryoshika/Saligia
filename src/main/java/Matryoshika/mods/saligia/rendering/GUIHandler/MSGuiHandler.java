package Matryoshika.mods.saligia.rendering.GUIHandler;

import Matryoshika.mods.saligia.items.ItemLibroSaligia;
import Matryoshika.mods.saligia.rendering.GUI.book.ContainerLibroSaligia;
import Matryoshika.mods.saligia.rendering.GUI.book.GUILibroSaligia;
import Matryoshika.mods.saligia.rendering.GUI.book.relics.GUILibroSaligiaRelics;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MSGuiHandler implements IGuiHandler {
	
	public static final int GUI_LIBROSALIGIA = 0;
	public static final int GUI_LIBROSALIGIARELICS = 1;


	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		return null;

	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
		case GUI_LIBROSALIGIA:{
			return new GUILibroSaligia();
			}
		case GUI_LIBROSALIGIARELICS: {
			return new GUILibroSaligiaRelics();
			}
		}
		
		return null;
	}

}
