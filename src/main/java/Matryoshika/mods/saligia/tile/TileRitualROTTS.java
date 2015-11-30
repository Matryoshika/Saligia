package Matryoshika.mods.saligia.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.blocks.saligia_Blocks;
import Matryoshika.mods.saligia.entities.misc.customLightningBolt;
import Matryoshika.mods.saligia.items.saligia_Items;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;

public class TileRitualROTTS extends TileEntity{
	
	private static final double RANGE = 64F;
	
	@Override
    public void updateEntity(){
		float f = 1.0F;
        float f1 = f * 0.6F + 0.4F;
        float f2 = f * f * 0.7F - 0.5F;
        float f3 = f * f * 0.6F - 0.7F;
		
		Random rand = new Random();
		
		if (!this.worldObj.isRemote){
		
		Class<EntityMob> mobs = EntityMob.class;
		List<EntityMob> inbox = worldObj.getEntitiesWithinAABB(mobs, AxisAlignedBB.getBoundingBox(this.xCoord - RANGE, this.yCoord - RANGE, this.zCoord - RANGE, this.xCoord + RANGE, this.yCoord + RANGE, this.zCoord + RANGE));
		if(worldObj.isAirBlock(xCoord, yCoord+1, zCoord) == true){
			for(EntityMob mob : inbox){
				if(!(mob instanceof IBossDisplayData)){
					mob.attackEntityFrom(DamageSource.generic, 1.5F);
					
					if(mob.getHealth() <= 1.6){
						worldObj.addWeatherEffect(new customLightningBolt(worldObj, mob.posX, mob.posY, mob.posZ));
						mob.setDead();
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
