package Matryoshika.mods.saligia.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.API.Rituals.IRitualSensor;
import Matryoshika.mods.saligia.blocks.saligia_Blocks;
import Matryoshika.mods.saligia.entities.misc.customLightningBolt;
import Matryoshika.mods.saligia.items.saligia_Items;
import net.minecraft.block.Block;
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

public class TileRitualCOTH extends TileEntity implements IRitualSensor{
	final int [][] GHASTLY_BLOCKS = new int [][]{
		{0,1,-4},{3,1,-3},{4,1,0},{3,1,3},{0,1,4},{-3,1,3},{-4,1,0},{-3,1,-3}
	};
	
	private static final double RANGE = 2F;
	
	public static String name = "Cognizance of the Hellmouth";
	
	@Override
    public void updateEntity(){
		int y = this.yCoord+1;
		int count = 0;
		int ticked = 1;
		
		if (!this.worldObj.isRemote){
		
		Class<EntityItem> items = EntityItem.class;
		List<EntityItem> inbox = worldObj.getEntitiesWithinAABB(items, AxisAlignedBB.getBoundingBox(this.xCoord - RANGE, this.yCoord - RANGE, this.zCoord - RANGE, this.xCoord + RANGE, this.yCoord + RANGE, this.zCoord + RANGE));
		if(worldObj.isAirBlock(xCoord, yCoord+1, zCoord) == true){
			for(EntityItem item : inbox){
				if(!item.isDead && item.getEntityItem() != null && item.getEntityItem().getItem() == Items.writable_book) {
					if (item.ticksExisted ==5)
						worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, saligia.MODID+":whispering1", 1.0F, 1.0F);
					
					if(item.ticksExisted >= 320){
						worldObj.addWeatherEffect(new customLightningBolt(worldObj, this.xCoord, y, this.zCoord));
						item.setDead();
						ItemStack iStack = new ItemStack(saligia_Items.LibroSaligia);
						Entity entity = new EntityItem(worldObj, this.xCoord+0.5, y+0.2, this.zCoord+0.5, iStack);
						worldObj.spawnEntityInWorld(entity);
						entity.motionX =0; entity.motionY =0; entity.motionZ =0;
						
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
	
	@Override
	public boolean isRitualMaster(Block block) {
		return true;
	}
	
}
