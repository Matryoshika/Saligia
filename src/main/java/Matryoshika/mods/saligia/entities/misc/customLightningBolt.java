package Matryoshika.mods.saligia.entities.misc;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class customLightningBolt extends EntityLightningBolt {

	private int lightningState;

	public long boltVertex;

	private int boltLivingTime;

	public customLightningBolt(World world, double x, double y, double z){
	super(world, 0, Double.MAX_VALUE, 0);
		 this.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
		 this.lightningState = 2;
		 this.boltVertex = this.rand.nextLong();
		 this.boltLivingTime = this.rand.nextInt(3) + 1;
	}
	public void onUpdate()
	{
		 //super.onUpdate();

		 if (this.lightningState == 2){
			 this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "ambient.weather.thunder", 10000.0F, 0.8F + this.rand.nextFloat() * 0.2F);
			 this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
		 }

		 --this.lightningState;

		 if (this.lightningState < 0){
			 if (this.boltLivingTime == 0){
				 this.setDead();
			 }
			 else if (this.lightningState < -this.rand.nextInt(10)){
				 --this.boltLivingTime;
				 this.lightningState = 1;
				 this.boltVertex = this.rand.nextLong();
			 }
		 }

		 if (this.lightningState >= 0){
			 if (this.worldObj.isRemote){
				 this.worldObj.lastLightningBolt = 2;
			 }
		 }
	}

	protected void entityInit() {}

	protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}

	protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}

	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderVec3D(Vec3 vec3){
		 return this.lightningState >= 0;
	}
}
