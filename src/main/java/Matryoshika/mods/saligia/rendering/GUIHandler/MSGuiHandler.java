package Matryoshika.mods.saligia.rendering.GUIHandler;

import Matryoshika.mods.saligia.items.ItemLibroSaligia;
import Matryoshika.mods.saligia.rendering.GUI.ContainerLibroSaligia;
import Matryoshika.mods.saligia.rendering.GUI.GUILibroSaligia;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MSGuiHandler implements IGuiHandler {
	
	public static final int GUI_LIBROSALIGIA = 0;


	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		return null;

	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
		case GUI_LIBROSALIGIA:
			return new GUILibroSaligia();
		}
		
		return null;
	}

}
