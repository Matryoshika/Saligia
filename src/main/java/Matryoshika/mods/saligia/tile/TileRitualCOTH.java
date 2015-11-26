package Matryoshika.mods.saligia.tile;

import java.util.List;
import java.util.Random;

import Matryoshika.mods.saligia.entities.misc.customLightningBolt;
import Matryoshika.mods.saligia.items.saligia_Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileRitualCOTH extends TileEntity{
	
	private static final double RANGE = 5F;
	
	@Override
    public void updateEntity(){
		int y = this.yCoord+1;
		
		if (!this.worldObj.isRemote){
		
		Class<EntityItem> items = EntityItem.class;
		List<EntityItem> inbox = worldObj.getEntitiesWithinAABB(items, AxisAlignedBB.getBoundingBox(xCoord - RANGE, yCoord - RANGE, zCoord - RANGE, xCoord + RANGE, yCoord + RANGE, zCoord + RANGE));
		if(worldObj.isAirBlock(xCoord, yCoord+1, zCoord) == true){
			for(EntityItem item : inbox){
				if(!item.isDead && item.getEntityItem() != null && item.getEntityItem().getItem() == Items.writable_book) {
					ItemStack stack = item.getEntityItem();
					
					if(item.ticksExisted >= 20){
						worldObj.addWeatherEffect(new customLightningBolt(worldObj, this.xCoord, y, this.zCoord));
						item.setDead();
						ItemStack iStack = new ItemStack(saligia_Items.LibroSaligia);
						Entity entity = new EntityItem(worldObj, this.xCoord+0.5, y+0.2, this.zCoord+0.5, iStack);
						worldObj.spawnEntityInWorld(entity);
						entity.motionX =0;
						entity.motionY =0;
						entity.motionZ =0;
						worldObj.playSoundAtEntity(entity, "mob.ghast.scream", 10F, 0.1F);
						
						}
					}
				}
			}				
		}
	}
	@Override
	public void writeToNBT(NBTTagCompound tag){
		getWorldObj();
		super.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
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
