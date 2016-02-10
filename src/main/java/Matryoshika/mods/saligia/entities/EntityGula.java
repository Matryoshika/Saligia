package Matryoshika.mods.saligia.entities;

import java.util.List;
import java.util.Random;
import java.util.Set;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.utils.math;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class EntityGula extends EntitySaligiaBoss{
	
	public EntityGula(World world){
        super(world);
    }

	private static final double RANGE = 20F;
	static Random random = new Random();
	public boolean regen;
	private World world;
   
	@Override
	public void onEntityUpdate(){
		if(this instanceof EntityGula){
			AxisAlignedBB box = this.boundingBox.expand(RANGE, RANGE, RANGE);
			Class<EntityItem> items = EntityItem.class;
			List<EntityItem> inbox = this.worldObj.getEntitiesWithinAABB(items, box);
	        	if(this.getHealth() < this.getMaxHealth()*0.2){
	        		regen = true;
	        	}
	        	if(regen == true){
	        		lookForBlock();
	        		
	        	}
	        	if(this.getHealth() == this.getMaxHealth()){
	        		regen = false;	
	        	}
	        	if(this.getHealth() >= 1){
	        		for (EntityItem entityItem : inbox){
	        			vacuumItems(this);
	    	    }
	        }
		}
		else {
			return;
		}
		super.onEntityUpdate();
    }
	
	private void lookForBlock(){
		Random random = worldObj.rand;
		int restartLook = 0;
		int maxLooks = 64;
		int range = 12;
		int safeRange = 2;
		for (int i = 0; i < 1 && restartLook < maxLooks; i++){
			
			
			int bX = (int) (posX + rand.nextInt(range) - rand.nextInt(range));
            int bY = (int) (posY + rand.nextInt(range) - rand.nextInt(range));
            int bZ = (int) (posZ + rand.nextInt(range) - rand.nextInt(range));
            if (getDistance(bX, bY, bZ) <= range && !worldObj.isRemote){
            	Block block = worldObj.getBlock(bX, bY, bZ);
                float hardness = block.getBlockHardness(worldObj, bX, bY, bZ);
                
                if (!(hardness < 0 || block.isAir(worldObj, bX, bY, bZ)) && this.worldObj.getTileEntity(bX, bY, bZ) == null){
                	if(!(getDistance(bX, bY, bZ) <= safeRange)){
                		block.dropBlockAsItem(worldObj, bX, bY, bZ, 0, 0);
                    	worldObj.setBlockToAir(bX, bY, bZ);
                    	worldObj.markBlockForUpdate(bX, bY, bZ);
                    	if(this.getHealth() < this.getMaxHealth()){
                    		this.heal(saligia.gulaHeal);
                    		}
                	}
                	else{
                		i--;
                    	restartLook++;
                	}
                }
                
                else{
                	i--;
                	restartLook++;
                }
            }
            else{
            	i--;
            	restartLook++;
            }
		}
	}
	@SuppressWarnings("unchecked")
	private void vacuumItems(Entity EntityGula){
		AxisAlignedBB box = EntityGula.boundingBox.expand(RANGE, RANGE, RANGE);
		Class<EntityItem> items = EntityItem.class;
		
		List<EntityItem> inbox = EntityGula.worldObj.getEntitiesWithinAABB(items, box);
		
			for (EntityItem entityItem : inbox) {
				double dx = (EntityGula.posX- entityItem.posX);
				double dy = (EntityGula.posY - entityItem.posY);
				double dz = (EntityGula.posZ - entityItem.posZ);
				double ddt = math.py3d(dx, dy, dz);
				entityItem.motionX += (dx/ddt/ddt)*0.05;
				entityItem.motionY += (dy/ddt/ddt)*0.01;
				entityItem.motionZ += (dz/ddt/ddt)*0.05;
				if (entityItem.posY < EntityGula.posY){
					entityItem.motionY += 0.005;
				}
				if (entityItem.posY > EntityGula.posY+4){
					entityItem.motionY -= 0.001;
				}
				entityItem.delayBeforeCanPickup = 20;
				
				if(entityItem.getDistanceToEntity(EntityGula) <= 1 || entityItem.ticksExisted >= 200){
					entityItem.setDead();
					
					Random eatRandom = EntityGula.worldObj.rand;
					int random2 = eatRandom.nextInt(10) + 1;
					if(random2 == 1)
					EntityGula.worldObj.playSound(EntityGula.posX, EntityGula.posY, EntityGula.posZ, "random.eat", 10F, 0.1F, true);
				
			}
		}
    }
	public boolean doesVacuum(){
        return true;
    }
    
}
