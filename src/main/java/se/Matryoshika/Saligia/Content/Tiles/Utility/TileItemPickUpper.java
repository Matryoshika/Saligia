/**
 * 
 */
package se.Matryoshika.Saligia.Content.Tiles.Utility;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.VanillaDoubleChestItemHandler;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.Content.Tiles.CustomTileClass;

/**
 * This class was created by Matryoshika Oct 13, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class TileItemPickUpper extends CustomTileClass implements ITickable{
	
	public static int TIMER = 0;
	public static final int LIMIT = 1 * 20;
	private final int RADII = 3;

	@Override
	public double[] colourScheme() {
		return new double[]{108, 0, 160};
	}
	
	@Override
	public void update() {
		if(worldObj.isRemote)
			return;
		
		TIMER++;
		
		if(TIMER >= LIMIT){
			TIMER = 0;
			activate();
		}
	}
	
	private void activate(){
		
		if(getItemHandler() != null){
			insert(getItemHandler());
		}
	}
	
	private void insert(IItemHandler inv){
		List<EntityItem> inbox = worldObj.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(getPos().add(-RADII, -RADII, -RADII), getPos().add(RADII+1, RADII+1, RADII+1)));
		
		for(EntityItem entity : inbox){
			ItemStack stack = entity.getEntityItem();

			for(int i = 0; i < inv.getSlots(); i++){
				if(inv.getStackInSlot(i) == null || (inv.getStackInSlot(i) != null && inv.getStackInSlot(i).getItem() == stack.getItem() && inv.getStackInSlot(i).stackSize < 64)){
					ItemStack over = inv.insertItem(i, stack, false);
					if(over != null)
						stack.stackSize -= over.stackSize;
					else{
						entity.setDead();
					}
					if(stack.stackSize <= 0)
						entity.setDead();
					break;
				}
			}
		}
	}
	
	private IItemHandler getItemHandler(){

		for(EnumFacing side : EnumFacing.VALUES){
			TileEntity te = worldObj.getTileEntity(pos.offset(side));
			if(te == null)
				continue;
			
			if(te instanceof TileEntityChest) {
				IItemHandler doubleChest = VanillaDoubleChestItemHandler.get((TileEntityChest) te);
				if(doubleChest != VanillaDoubleChestItemHandler.NO_ADJACENT_CHESTS_INSTANCE)
					return doubleChest;
			}

			IItemHandler ret = te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side) ?
					te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side) : null;

				if(ret == null && te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null))
					ret = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

				return ret;
			}
		
		return null;
		}
}
