package Matryoshika.mods.saligia.tile.soulsystem;

import java.util.List;

import Matryoshika.mods.saligia.items.saligia_Items;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileSoulBrazier extends TileEntity {
	
	private static final double RANGE = 2F;
	
	public int capacity;
	public int amount;
	
	public static String TAG_ENERGY ="energy";
	public static String TAG_MAX ="max";
	public static String TAG_CANGETMORE ="stillEmpty";
	
	boolean canGetMore;
	
	public void TESoulBrazier(){
		this.capacity = Matryoshika.mods.saligia.utils.math.SoulBrazierMax();
	}
	
	
	@Override
	public void updateEntity() {
		
		NBTTagCompound tag = new NBTTagCompound();
		
		
		//Import energy, either through items, or straight from generators
		
		Class<EntityItem> items = EntityItem.class;
		List<EntityItem> inbox = worldObj.getEntitiesWithinAABB(items, AxisAlignedBB.getBoundingBox(this.xCoord +0.5 - RANGE, this.yCoord +0.5 - RANGE, this.zCoord +0.5 - RANGE, this.xCoord -0.5 + RANGE, this.yCoord -0.5 + RANGE, this.zCoord -0.5 + RANGE));
		if(worldObj.isAirBlock(xCoord, yCoord+1, zCoord) == true){
			for(EntityItem item : inbox){
			
				ItemStack stack = item.getEntityItem();
				if(amount < capacity && canGetMore == true && !item.worldObj.isRemote){
					
						if(stack != null && stack.getItem() == saligia_Items.AnimalSoul){
							item.setDead();
							amount += Integer.decode("0x2");
							//tag.setInteger("currentStorage", tag.getInteger("currentStorage") + Integer.decode("0x2"));
						}
						if(stack != null && stack.getItem() == saligia_Items.VillagerSoul){
							
							item.setDead();
							amount += 10;
							//tag.setInteger(TAG_ENERGY, amount + Integer.decode("0x6"));
						}
						if(stack != null && stack.getItem() == saligia_Items.ZombieSoul){
							item.setDead();
							amount += 10;
							//tag.setInteger(TAG_ENERGY, amount + Integer.decode("0x1"));
						}
						if(stack != null && stack.getItem() == saligia_Items.BuffMobSoul){
							item.setDead();
							amount += 10;
							//tag.setInteger(TAG_ENERGY, amount + Integer.decode("0x4"));
						}
						markDirty();
					
				}
			
				if(amount >= capacity){
					amount = capacity;
					canGetMore = false;
				}
				if(amount < capacity){
					canGetMore = true;
				}
			}
		}
		
		
		//Export energy to linkers, for other use
		
		
	}
	
	public int getSpaceLeft(){
		return Math.max(0, capacity-amount);
	}
	
	
	
	
	
	
	
	
	
	@Override
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setInteger("amount", amount);
		tag.setInteger("maxStorage", capacity);
		tag.setBoolean("notFilled", canGetMore);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		amount = tag.getInteger("amount");
		capacity = tag.getInteger("maxStorage");
		canGetMore = tag.getBoolean("notFilled");
	}
	
	@Override
	public Packet getDescriptionPacket(){
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 17, nbttagcompound);
    }
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
        readFromNBT(pkt.func_148857_g());
    }

}
