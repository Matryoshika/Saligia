package Matryoshika.mods.saligia.tile;

import java.util.List;

import Matryoshika.mods.saligia.items.saligia_Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileRitualCOTH extends TileEntity{
	
	private static final double RANGE = 10F;
	
	@Override
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
	}
	
	@Override
    public void updateEntity(){
		int y = this.yCoord+1;
		
		Class<EntityItem> items = EntityItem.class;
		List<EntityItem> inbox = worldObj.getEntitiesWithinAABB(items, AxisAlignedBB.getBoundingBox(xCoord - RANGE, yCoord - RANGE, zCoord - RANGE, xCoord + RANGE, yCoord + RANGE, zCoord + RANGE));
		if(worldObj.isAirBlock(xCoord, yCoord+1, zCoord) == true){
			for(EntityItem item : inbox){
				if(!item.isDead && item.getEntityItem() != null && item.getEntityItem().getItem() == Items.writable_book) {
					ItemStack stack = item.getEntityItem();
					if(item.ticksExisted >= 200){
						item.setDead();
						ItemStack iStack = new ItemStack(saligia_Items.LibroSaligia);
						Entity entity = new EntityItem(worldObj, this.xCoord, this.yCoord+1, this.zCoord, iStack);
						worldObj.spawnEntityInWorld(entity);
					}
				}
			}
				
		}
		
		
		/*
		
		
		
		
		AxisAlignedBB box = this.getRenderBoundingBox().expand(RANGE, RANGE, RANGE);
		Class<EntityItem> items = EntityItem.class;
		List<EntityItem> inbox = worldObj.getEntitiesWithinAABB(items, box);
		if(worldObj.isAirBlock(xCoord, y, zCoord) == true){
			if(worldObj.checkNoEntityCollision(box) == true){System.out.println("He's touching be, boss");}
			if(inbox == Items.writable_book){
				for(EntityItem entityItem : inbox){
					System.out.println("Dis be book, boss");
					if(entityItem.ticksExisted >= 20){
						entityItem.setDead();
					}
				}
			}
			else{
				return;
			}
		}
		else{
			return;
		}
		
		*/
	}
	
}
