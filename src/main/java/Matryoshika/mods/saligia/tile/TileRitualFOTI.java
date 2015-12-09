package Matryoshika.mods.saligia.tile;

import java.util.List;
import java.util.Random;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.entities.misc.customLightningBolt;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;

public class TileRitualFOTI extends TileEntity{
	
	private static final double RANGE = 3F;
	
	@Override
    public void updateEntity(){
		
		Random rand = new Random();
		
		if (!this.worldObj.isRemote){
		
		Class<EntityMob> mobs = EntityMob.class;
		List<EntityMob> inbox = worldObj.getEntitiesWithinAABB(mobs, AxisAlignedBB.getBoundingBox(this.xCoord -1, this.yCoord - RANGE, this.zCoord-1, this.xCoord+1, this.yCoord + RANGE, this.zCoord+1));
		if(worldObj.isAirBlock(xCoord, yCoord+1, zCoord) == true){
			for(EntityMob mob : inbox){
				if(!(mob instanceof IBossDisplayData)){
					int chance = rand.nextInt(10);
					if(chance > 5){
						if(mob instanceof EntityCreeper){
							EntityItem skull = new EntityItem(mob.worldObj, mob.posX, mob.posY, mob.posZ, new ItemStack(Items.skull, 1, 4));
							mob.worldObj.spawnEntityInWorld(skull);
						}
						if(mob instanceof EntityZombie){
							EntityItem skull = new EntityItem(mob.worldObj, mob.posX, mob.posY, mob.posZ, new ItemStack(Items.skull, 1, 2));
							mob.worldObj.spawnEntityInWorld(skull);
						}
						if(mob instanceof EntitySkeleton){
							if(((EntitySkeleton) mob).getSkeletonType() == 0){
								EntityItem skull = new EntityItem(mob.worldObj, mob.posX, mob.posY, mob.posZ, new ItemStack(Items.skull, 1, 0));
								mob.worldObj.spawnEntityInWorld(skull);
							}
							if(((EntitySkeleton) mob).getSkeletonType() == 1){
								EntityItem skull = new EntityItem(mob.worldObj, mob.posX, mob.posY, mob.posZ, new ItemStack(Items.skull, 1, 1));
								mob.worldObj.spawnEntityInWorld(skull);
							}
							
						}
					}
					
					
					
					// 0 skeleton, 1 wither, 2 zombie, 3 null, 4 creeper
					
					mob.setDead();
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
