package Matryoshika.mods.saligia.rendering.GUI.book;

import Matryoshika.mods.saligia.items.ItemLibroSaligia;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ContainerLibroSaligia extends Container{
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	@Override
	public void putStackInSlot(int slot, ItemStack stack) {

	}

}
