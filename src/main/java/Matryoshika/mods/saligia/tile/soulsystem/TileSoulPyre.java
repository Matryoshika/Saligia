package Matryoshika.mods.saligia.tile.soulsystem;

import java.util.Random;

import Matryoshika.mods.saligia.entities.misc.saligiaAvaritiaParticle;
import Matryoshika.mods.saligia.entities.misc.saligiaPyreParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileSoulPyre extends TileEntity{
	
	int timer = 0;
	int particleTimer = 0;

	@Override
	public void updateEntity() {
		
		TileEntity tile = worldObj.getTileEntity(this.xCoord, this.yCoord-1, this.zCoord);
		
		if(tile != null && tile instanceof TileSoulBrazier && !(tile instanceof TileSoulObelisk)){
			fillStorage(tile, 1, 1);
		}
		if(tile != null && tile instanceof TileSoulObelisk && !(tile instanceof TileSoulNexus)){
			fillStorage(tile, 16, 2);
		}
		if(tile != null && tile instanceof TileSoulNexus){
			fillStorage(tile, 256, 3);
		}
		
		
		particleTimer++;
		if(particleTimer >= 5 && tile != null && tile instanceof TileSoulBrazier){
			
			Random rand = new Random();
			double motionX = rand.nextGaussian() * 0.02D;
			double motionY = rand.nextGaussian() * 0.02D;
			double motionZ = rand.nextGaussian() * 0.02D;
			EntityFX soylPyreFX = new saligiaPyreParticle(worldObj, this.xCoord+0.5, this.yCoord+0.75, this.zCoord+0.5, motionX*20, motionY*0.8, motionZ*20 );
			Minecraft.getMinecraft().effectRenderer.addEffect(soylPyreFX);
			
			
	    	particleTimer = 0;
		}
		
	}
	
	public void fillStorage(TileEntity tile, int extra, int tier){
		timer++;
		if(timer >= 256){
			timer = 0;
			if(tier == 1){
				TileSoulBrazier te = (TileSoulBrazier) tile;
				if(te.amount < Math.pow(Integer.decode("0x29A"), tier))
				te.amount += 1*extra;
			}
			if(tier == 2){
				TileSoulObelisk te = (TileSoulObelisk) tile;
				if(te.amount < Math.pow(Integer.decode("0x29A"), tier))
				te.amount += 1*extra;
			}
			if(tier == 3){
				TileSoulNexus te = (TileSoulNexus) tile;
				if(te.amount < Math.pow(Integer.decode("0x29A"), tier))
				te.amount += 1*extra;
			}
		}
	}

	
	
	
	
	@Override
	public void writeToNBT(NBTTagCompound tag){
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
