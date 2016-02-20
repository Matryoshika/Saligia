package Matryoshika.mods.saligia.tile;

import Matryoshika.mods.saligia.items.runes.ItemRune;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class SlotRunicScribe extends Slot{
    private final int slotIndex;
    public final IInventory inventory;


    public SlotRunicScribe(IInventory iinventory, int id, int x, int y)
    {
    	super(iinventory, id, x, y);
        this.inventory = iinventory;
        this.slotIndex = id;
        this.xDisplayPosition = x;
        this.yDisplayPosition = y;
    }

    @Override
    public boolean isItemValid(ItemStack stack){
    	if(slotIndex == 0 && stack.getItem() instanceof Item && !(stack.getItem() instanceof ItemRune || stack.getItem() instanceof ItemBlock)){
    		return true;
    	}
    	if(slotIndex < 4 && slotIndex > 0 && stack.getItem() instanceof ItemRune){
    		return true;
    	}
    	return false;
    }
    
    @Override
    public ItemStack getStack(){
    	return this.inventory.getStackInSlot(this.slotIndex);
    }
}